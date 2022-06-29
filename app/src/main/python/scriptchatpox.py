from nltk.chat.util import Chat


def main(indata):
    messages = [
        ["my name is shalash", ["hi shalash"]],
        ["how are you", ["I am fine"]]
    ]
    chat = Chat(messages)

    output = chat.respond(indata)

    return "" + str(output)

main("my name is shalash")