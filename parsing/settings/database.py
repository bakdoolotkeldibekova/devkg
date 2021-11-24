from optparse import Option

import psycopg2

from decouple import config as conf
try:
    try:
        # connect to exist base
        connection = psycopg2.connect(
            host=conf('DB_HOST'),
            user=conf('DB_USER'),
            password=conf('DB_PASSWD'),
            database=conf('DB_BASENAME')
        )
        with connection.cursor() as cursor:
            cursor.execute(
                'select version();'
            )
            print(f"Server version {cursor.fetchone()}")
    except Exception as ex:
        print('[INFO] Error while with working PostgreSQL', ex)
    finally:
        if connection:
            connection.close()
            print("[INFO] PostgreSQL connection closed")
except:
    pass
