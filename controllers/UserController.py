from server_app import app
from flask import jsonify

@app.route("/test", methods='GET')
def test():
    print("It's work!")
    
    reponse = {
        "msg": "it's Working!"
    }
    return jsonify(reponse)