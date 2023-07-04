from flask import Flask
from controllers.UserController import user_blueprint

app = Flask(__name__)

# Blueprints
app.register_blueprint(user_blueprint, url_prefix='/users')



print()
if __name__ == "__main__":
    app.run(host="127.0.0.1", port=8080, debug=True)
