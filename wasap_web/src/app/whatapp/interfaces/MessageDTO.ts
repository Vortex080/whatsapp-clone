export interface ChatDTO {
    id:       number;
    users:    number[];
    messages: Message[];
}

export interface Message {
    idAuthor: number;
    text:     string;
    date:     string;
    chatId:   number;
}
