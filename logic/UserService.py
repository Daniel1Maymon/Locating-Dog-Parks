import mysql.connector
import json

db = mysql.connector.connect(
    host="localhost", user="root", passwd="root", database="dog_log"
)

my_cursor = db.cursor()


def add_user(username, email):
    user_exists = check_if_user_exists(email)

    if user_exists:
        return False

    my_cursor.execute(
        "INSERT INTO User (name, email) VALUES (%s, %s)", (username, email)
    )
    db.commit()

    my_cursor.execute("SELECT * FROM User WHERE userId = LAST_INSERT_ID()")
    inserted_row = my_cursor.fetchone()

    if inserted_row:
        new_added_user = {
            "name": inserted_row[0],
            "email": inserted_row[1],
            "id": inserted_row[2],
        }
        return new_added_user

    return None


def check_if_user_exists(email):
    my_cursor.execute(f"SELECT * FROM User WHERE email = '{email}'")

    inserted_row = my_cursor.fetchone()


    return inserted_row


def login(username, email):
    user_exists = check_if_user_exists(email)

    return True if user_exists else False
