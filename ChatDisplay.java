package domgame;

public class ChatDisplay extends Server
{
	public static String chatText;
	
	public String addChat(String chat)
	{
		chatText = chat;
		return chatText;

	}

	public String returnChat()
	{
		return chatText;
	}

}
