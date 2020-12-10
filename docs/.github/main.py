import codecs
import sys

import requests

URL = r"https://api.github.com/repos/Ktt-Development/MyAnimeList-Java-API"

def main():
    if len(sys.argv) <= 1:
        print("Failed to update file (no oauth token provided)")
        return
    token = sys.argv[1]

    headers = {
        "Accept-Charset": "UTF-8",
        "Authorization": "token " + token
    }

    response = requests.get(URL, headers=headers)

    with codecs.open("../_data/repository.json", 'w', encoding="utf-8") as file:
        file.write(response.text)
        file.close()

    return


if __name__ == "__main__":
    main()

