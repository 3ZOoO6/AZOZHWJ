from flask import Blueprint, render_template

frontend = Blueprint('frontend', __name__)

@frontend.route('/')
def home():
    return render_template('dark01.html')

@frontend.route('/shadow')
def shadow():
    return render_template('shadow02.html')

@frontend.route('/mystic')
def mystic():
    return render_template('mystic03.html')

@frontend.route('/mobirise')
def mobirise():
    return render_template('mobirise_intro.html')
