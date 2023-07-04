import sys
import os
from flask import jsonify, Blueprint

user_blueprint = Blueprint("user_blueprint", __name__)

# Get the current directory and the parent directory
current_dir = os.path.dirname(__file__)
parent_dir = os.path.dirname(current_dir)

sys.path.append(parent_dir)

# from server_app import app


@user_blueprint.route("/test", methods=["GET"])
def test():
    print("It's work!")
    
    reponse = {
        "msg": "it's Working!"
    }
    return jsonify(reponse)