from os.path import dirname, realpath, isfile
from json import dump, load


class JsonManager:
    def __init__(self):
        self.path = dirname(realpath(__file__))
        self.create_db('\\data\\usersdb.json')
        self.create_db('\\data\\passworddb.json')

    def create_db(self, file):
        path_data_json = self.path + file
        if not isfile(path_data_json):
            try:
                with open(path_data_json, 'w') as f:
                    dump([], f, indent=2)
                return True
            except IOError as e:
                print(f"Error creating database file {file}: {e}")
                return False
        else:
            return False

    def add_to_list(self, file, item):
        data = self.get_file_contents(file)
        if data is False:
            return False

        # Avoid duplicates if necessary
        if item not in data:
            data.append(item)

        path_data_json = self.path + file
        if isfile(path_data_json):
            try:
                with open(path_data_json, 'w') as f:
                    dump(data, f, indent=2)
                return True
            except IOError as e:
                print(f"Error writing to file {file}: {e}")
                return False
        else:
            return False

    def remove_from_list(self, file, identifier):
        data = self.get_file_contents(file)
        if data is False:
            return False

        updated_data = [item for item in data if item.get('id') != identifier]

        path_data_json = self.path + file
        if isfile(path_data_json):
            try:
                with open(path_data_json, 'w') as f:
                    dump(updated_data, f, indent=2)
                return True
            except IOError as e:
                print(f"Error writing to file {file}: {e}")
                return False
        else:
            return False

    def get_file_contents(self, file):
        path_data_json = self.path + file
        if isfile(path_data_json):
            try:
                with open(path_data_json) as f:
                    data = load(f)
                return data
            except (IOError, ValueError) as e:
                print(f"Error reading file {file}: {e}")
                return False
        else:
            return False

    def get_user_by_credentials(self, file, email, userpass):
        data = self.get_file_contents(file)

        if data is False:
            return None

        for user in data:
            if user.get('email') == email and user.get('userpass') == userpass:
                return user

        return None

    def get_passwords_by_user_id(self, user_id):
        # Change this path if the password file is located elsewhere
        passwords_file = '\\data\\passworddb.json'
        data = self.get_file_contents(passwords_file)

        if data is False:
            return None

        # Filter passwords by userId
        user_passwords = [
            password for password in data if password.get('userId') == user_id]

        return user_passwords
