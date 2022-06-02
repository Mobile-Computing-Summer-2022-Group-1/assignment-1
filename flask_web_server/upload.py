import os
from flask import Flask, flash, request, redirect, url_for
from werkzeug.utils import secure_filename

UPLOAD_FOLDER = 'files'
ALLOWED_EXTENSIONS = {'mp4'}

app = Flask(__name__)
app.secret_key = "super secret key"
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/', methods=['GET', 'POST'])
def upload_file():
    if request.method == 'POST':
        print("POST request received")
        # check if the post request has the file part
        if 'file' not in request.files:
            print("POST request has no file part")
            flash('No file part')
            return redirect(request.url)
        file = request.files['file']
        print(f"File part: {file.filename}")
        # If the user does not select a file, the browser submits an
        # empty file without a filename.
        if file.filename == '':
            print("Filename empty")
            flash('No selected file')
            return redirect(request.url)
        if file and allowed_file(file.filename):
            print("File is allowed. Saving file")
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            return redirect(url_for('download_file', name=filename))
        else:
            print("File not allowed")
    else:
        print("GET request received")

    return '''
    <!doctype html>
    <title>Upload new File</title>
    <h1>Upload new File</h1>
    <form method=post enctype=multipart/form-data>
      <input type=file name=file>
      <input type=submit value=Upload>
    </form>
    '''
