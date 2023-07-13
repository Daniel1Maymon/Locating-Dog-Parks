import mysql.connector

## Create mySQL connection AND Create a new DB##
# db = mysql.connector.connect(
#     host="localhost", user="root", passwd="root"
# )

# my_curser = db.cursor()
# my_curser.execute("CREATE DATABASE dog_log")


## Connect to dog_log DB AND Create new Table##
db = mysql.connector.connect(
    host="localhost", user="root", passwd="root", database="dog_log"
)

my_curser = db.cursor()

#  Add new TABLE:
# my_curser.execute(
#     "CREATE TABLE User (name VARCHAR(50), email VARCHAR(255)) userID int PRIMARY KEY AUTO_INCREMENT)"
# )


# my_curser.execute("DESCRIBE User")

# for x in my_curser:
#     print(x)

## Insert New User
# my_curser.execute("INSERT INTO User (name, email) VALUES (%s, %s)", ('Daniel', 'daniel@gmail.com'))
# db.commit()

# my_curser.execute("SELECT * FROM User")
# for x in my_curser:
#     print(x)