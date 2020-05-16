import json

def read_json_file(filepath: str):
    '''
        Simple file that returns json formatted file
        as dictionary
        
        Args:
            filepath: Path of file.

        Returns:
            Dictionary of data
    '''
    with open(filepath, 'rt') as read_file:
        return json.load(read_file)