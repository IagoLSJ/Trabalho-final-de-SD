from os.path import dirname, realpath, isfile
from json import dump, dumps, load


class JsonManager : 
    def __init__(self):
        self.path = dirname(realpath(__file__))
        self.create_db('\\data\\usersdb.json')
        self.create_db('\\data\\passworddb.json')

    def create_db(self, file):
        path_data_json = self.path + file
        if not isfile(path_data_json):
            with open(path_data_json, 'w') as f:
                dump([], f, indent=2)
            return True
        else : 
            return False
        
    def get_file_contents(self, file) :
        path_data_json = self.path + file
        if isfile(path_data_json):
            with open(path_data_json) as f:
                data = load(f)
            return data
        else :
            return False

    def add_to_list(self, file, item) :
        data = self.get_file_contents(file)
        data.append(item)
        path_data_json = self.path + file
        if isfile(path_data_json):
            with open(path_data_json, 'w') as f:
                dump(data, f, indent=2)
            return True
        else : 
            return False
    
    def remove_from_list(self, file, identifier) :
        pass

