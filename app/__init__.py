from flask import Flask
from app.routes.frontend import frontend

def create_app():
    app = Flask(__name__)
    app.register_blueprint(frontend)
    return app
