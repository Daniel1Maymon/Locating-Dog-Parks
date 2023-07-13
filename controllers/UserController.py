import json
import sys
import os
from flask import jsonify, Blueprint, request, make_response
# from ..logic import UserService
from logic import UserService

user_blueprint = Blueprint("user_blueprint", __name__)

# Get the current directory and the parent directory
current_dir = os.path.dirname(__file__)
parent_dir = os.path.dirname(current_dir)

sys.path.append(parent_dir)

# from server_app import app


@user_blueprint.route("/", methods=["POST"])
def create_user():
    request_body = json.loads(request.get_data(as_text=True))
    username = ''
    email = ''
    if 'username' not in request_body or 'email' not in request_body:
        error_msg = {'error': 'Username or Email field is missing'}
        return make_response(jsonify(error_msg), 400)
        
             
    username = request_body['username']
    email = request_body['email']
     
    reponse = UserService.add_user(username, email)
    return jsonify(reponse)
