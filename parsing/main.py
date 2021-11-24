import time

import requests
from fake_useragent import FakeUserAgent
from decouple import config as conf
from bs4 import BeautifulSoup as Soup

fake = FakeUserAgent()


class ParsingVacancies:
    def __init__(self):
        self.base_url = 'https://devkg.com'
        self.current_url = 'https://devkg.com/ru/jobs'
        self.header = {'User-Agent': fake.random}
        self.pages_count = conf('PAGES_COUNT')

    def parse_vacancies(self):
        for i in range(int(self.pages_count)):
            time.sleep(.5)
            response = requests.get(url=self.current_url + '?page=' + str(i), headers=self.header)
            if response.status_code == 200:
                soup = Soup(response.content, 'html.parser')
                main_item = soup.findAll('article', class_='item')
                for item in main_item:
                    company = item.find('div', {'class': 'jobs-item-field company'}).get_text(strip=True).replace(
                        'Компания', '')
                    position = item.find('div', {'class': 'jobs-item-field position'}).get_text(strip=True).replace(
                        'Должность', '')
                    s_item = item.find('a', class_='link').get('href')
                    """Detail request"""
                    time.sleep(.5)
                    detail_req = requests.get(url=self.base_url + s_item, headers=self.header)
                    if detail_req.status_code == 200:
                        detail = Soup(detail_req.content, 'html.parser')
                        try:
                            detail.find('div', class_='archived').get_text()
                            print('Вакансия не актуальна')
                            continue
                        except AttributeError:
                            pass
                        price = detail.find('div', class_='price').get_text(strip=True).replace('Оклад', '')
                        description = detail.find('main', class_='job-body').get_text(strip=True)
                        stack = detail.find_all('div', class_='type')
                        telegram = None
                        skype = None
                        email = None
                        phone = None
                        type_vac = None
                        for st in stack:
                            match st.findNext().get_text(strip=True):
                                case "Telegram":
                                    telegram = st.findNext().findNext().get_text(strip=True)
                                case "Skype":
                                    skype = st.findNext().findNext().get_text(strip=True)
                                case "E-Mail":
                                    email = st.findNext().findNext().get("href")
                                case "Телефон":
                                    phone = st.findNext().findNext().get_text(strip=True)
                                case "Тип":
                                    type_vac = st.findNext().findNext().get_text(strip=True)
                        print('telegram:', telegram, 'skype:', skype, 'email:', email, 'type:', type_vac, 'phone:',
                              phone)
                        # print({"Company:": company, "Position:": position, "Type:": type_vac, "Price:": price,
                        #        "E-mail:": email})
            print(f'[INFO] Страница {i + 1} успешно скатан')


ParsingVacancies().parse_vacancies()
