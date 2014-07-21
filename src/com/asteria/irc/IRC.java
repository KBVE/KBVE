package com.asteria.irc;
//IRC.Bot-Base - IRC Bot version 3.0
//----------------------------------------------------------------------------
//This java library includes the Java PircBot as the core library and this 
//bots purpose is anything. From responding to messages via channel, pulling
//logs from website, etc.
//
//
//**NEW** in version 2.0. Added SSL support for IRC servers. Using PircBot 1.5
//core, and Java's SSL Socket Factory, PircBot.java determines when to use SSL
//when calling a new connection. Example:
//
//       bot.connect(hostname, port, new TrustingSSLSocketFactory());
//
//Standard way non-SSL below:
//
//       bot.connect(hostname, port);
//
//@author	Adam Brenner <aebrenne@uci.edu>
//@version	3.0



import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

import org.jibble.pircbot.Colors;
import org.jibble.pircbot.PircBot;
import com.asteria.world.World;


public class IRC extends PircBot {
	
	private String botName;
	private String ircAddress;
	private String nickServPass;
	private String botAdmin;
	private String botAdminChan;
	private String botTrigWord;
	private int ircPort;
	private PircBot relayBot;
	private final static boolean ISVERBOSE = true;
	
	public IRC(String botName, String ircAddress, String nickServPass, String botAdmin, String botAdminChan, String botTrigWord, int ircPort)
	{
		this.botName = botName;
		this.ircAddress = ircAddress;
		this.nickServPass = nickServPass;
		this.botAdmin = botAdmin;
		this.botAdminChan = botAdminChan;
		this.botTrigWord = botTrigWord;
		this.ircPort = ircPort;

		this.setName(botName);
		this.setLogin(botName);
		this.setVersion("IRC Client 3.0");
		
		if(ISVERBOSE)
			this.setVerbose(true);
	}
	
	/**
	 * The last <code>@param relayBot</code> is a new feature added in version 3.0 allowing
	 * us to send a message to another instance of this bot, which is most
	 * likely connected to another IRC network. We call this the relayBot.
	 * 
	 * @author	Adam Brenner <aebrenne@uci.edu>
	 * @since	3.0
	 */
	public IRC(String botName, String ircAddress, String nickServPass, String botAdmin, String botAdminChan, String botTrigWord, int ircPort, PircBot relayBot)
	{
		this(botName, ircAddress, nickServPass, botAdmin, botAdminChan, botTrigWord, ircPort);
		this.relayBot = relayBot;
	}
	
	
	/**
	 * We override the onConnect method from the PircBot class to message the
	 * nickserv and identify our selves. Once we are logged in, we can join 
	 * channels.
	 * <br /><br />
	 * We assume the bot is already registered with the nickserver.
	 * 
	 * @author	Adam Brenner <aebrenne@uci.edu>
	 * @since	1.0
	 * @see org.jibble.pircbot.PircBot#onConnect()
	 */
	public void onConnect()
	{
		sendMessage("nickserv","identify " + nickServPass);
	}
	

	/**
	 * When we get disconnected from the server, this method gets called. The
	 * idea with the sleep function, is to allow the remote IRC server, to find
	 * out that we actually got disconnect. This method -- most of the time --
	 * gets called when a <strong>PING OUT</strong> happens.
	 * <br /><br />
	 * By default and hard coded here, we wait 15 seconds before trying to
	 * reconnect to the server.
	 * 
	 * @author	Adam Brenner <aebrenne@uci.edu>
	 * @since	1.0
	 * @see org.jibble.pircbot.PircBot#onDisconnect()
	 */
	@SuppressWarnings("static-access")
	public void onDisconnect()
	{
		while(!isConnected()) {
			try {
				Thread.currentThread().sleep(15000); // sleeping for 15 seconds
				connect(ircAddress, ircPort);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * This onNotice method is called when any server notice - to us or not - is
	 * received by the bot. For this purpose, we simply check to see if we have
	 * correctly identify with the nickserv. We then tell the botAdmin we joined
	 * the server and identified correctly;
	 * 
	 * @author	Adam Brenner <aebrenne@uci.edu>
	 * @since	1.0
	 * @see org.jibble.pircbot.PircBot#onNotice(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void onNotice(String sourceNick, String sourceLogin, String sourceHostname, String target, String notice)
	{
		if(sourceNick.equalsIgnoreCase("nickserv") && target.equalsIgnoreCase(botName))
			if(notice.contains("Password accepted - you are now recognized."))
				sendMessage(botAdmin, "I identify with the nickserver.");
	}
	
	
	/**
	 * This onInvite method is called when the chanserv invites us into a
	 * channel. This invite should be for this user, however depending on the 
	 * IRC server setup, and the access this bot may have, lets only respond
	 * to invite requests for us!
	 * 
	 * @author 	helloadam
	 * @since	1.0
	 * @see org.jibble.pircbot.PircBot#onInvite(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void onInvite(String nick, String srcNick, String srcLogin, String srcHost, String channel)
	{
		if(nick.equalsIgnoreCase(botName))
			joinChannel(channel);
	}

	
	/**
	 * This onJoin method is called when we join a channel. We can join channels
	 * a number of ways: SAJOIN, Invite and manually. The onJoin method is the
	 * the same method that is used when anyone joins a channel. For this reason
	 * we need to be careful in the way we treat onJoin. 
	 * <br /><br />
	 * The purpose of this function, as of 1.0, is to simply notify the botAdmin
	 * that the bot joined a channel.
	 * 
	 * @author	Adam Brenner <aebrenne@uci.edu>
	 * @since	1.0
	 * @see org.jibble.pircbot.PircBot#onJoin(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void onJoin(String channel, String nick, String login, String hostname)
	{
		if(nick.equalsIgnoreCase(botName))
			sendMessage(botAdmin, "I joined " + channel);
	}

	
	/**
	 * This onKick method is called when anyone gets kicked from a channel. With
	 * that in mind, we only care about if we get kicked. If we do get kicked
	 * then lets go ahead and rejoin the channel and notify the botAdmin.
	 * 
	 * @author	Adam Brenner <aebrenne@uci.edu>
	 * @since	1.0
	 * @see org.jibble.pircbot.PircBot#onKick(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void onKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason)
	{
		if(recipientNick.equalsIgnoreCase(botName)) {
			sendMessage(botAdmin, "I was kicked by " + kickerNick + " from " + channel + " for: " + Colors.PURPLE + reason);
			joinChannel(channel);
		}
	}
	
	
	/**
	 * This onPrivateMessage is when we get a private message sent to us from
	 * a user or one of the IRCD services. We are going to add some checks like
	 * prevent a loop - the bot messaging it self. The point of this bot is to
	 * not be noticed, so lets go ahead and ignore all private messages. Unless
	 * its some sort of command from the botAdmin.
	 * 
	 * @author	Adam Brenner <aebrenne@uci.edu>
	 * @since	1.0
	 * @see org.jibble.pircbot.PircBot#onPrivateMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void onPrivateMessage(String sender, String login, String hostname, String message)
	{
		if(sender.equalsIgnoreCase(botAdmin) && !sender.equalsIgnoreCase(botName))
			_botMsgFunctions(sender, sender, login, hostname, message);
		else
			sendMessage(botAdmin, sender +" sent: " + Colors.PURPLE + message);
	}

	
	/**
	 * This onMessage method is called when any message is received from any
	 * channel that a bot is in. The message can come from us, or from another
	 * user. Keep in mind of endless loops so if the bot speaks, most likely
	 * want to ignore that message.
	 * <br /><br />
	 * A new feature in 3.0 is the ability for the entire channel to have admin
	 * access to the bot. So I went ahead and pushed this code to the main
	 * branch and this is useful option.
	 * 
	 * @author	Adam Brenner <aebrenne@uci.edu>
	 * @since 	1.0
	 * @see org.jibble.pircbot.PircBot#onMessage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{
		if(!sender.equalsIgnoreCase(botName))
		{
			_botMsgFunctions(channel, sender, login, hostname, message);
			message.replaceFirst("[LobbyBot]", "");
			World.sendMessage("[IRC]["+sender+"] " + message);
		}
	}
	
	
	/**
	 * This onSocketMessage is called when any message has been received from 
	 * socket and is ready to be processed. We will be handling the security
	 * of this socket request, as we have no idea who sent it, and if it has
	 * some funky code/message.
	 * 
	 * @author	Adam Brenner <aebrenne@uci.edu>
	 * @since	2.0
	 */
	public void onSocketMessage(String message, InetAddress inetAddress)
	{
		// This function is left blank for a reason. Replace with your hearts
		// desire (or not).
	}
	
	
	/**
	 * This method controls all messages that the bot receives that are designed
	 * to perform some sort of actions. Like join a channel, leave a channel, 
	 * speak in a channel, etc.
	 * 
	 * @access	private
	 * @author	hellloadam
	 * @since	3.0
	 */
	private void _botMsgFunctions(String channel, String sender, String login, String hostname, String message)
	{
		String[] msg = message.split(" ");
		if(msg.length < 1 || !msg[0].equalsIgnoreCase(botTrigWord))
			return;

		// New option in version 3.0 is to give a channel entire bot access.
		// This is that function now. If the message is in the form of a PM then
		// both the channel and sender are the same.
		
		if(channel.equalsIgnoreCase(botAdminChan) || (sender.equalsIgnoreCase(botAdmin) && channel.equalsIgnoreCase(botAdmin))) {
			if(msg[1].equalsIgnoreCase("ping"))
				sendMessage(channel, "pong");
			else if(msg[1].equalsIgnoreCase("join") && msg.length == 3)
				joinChannel(msg[2]);
			else if(msg[1].equalsIgnoreCase("part") && msg.length == 3)
				partChannel(msg[2]);
			else if(msg[1].equalsIgnoreCase("say") && msg.length > 3) {
				String response = msg[3];
				for(int i = 4; i < msg.length; i++)
					response += " " + msg[i];
				sendMessage(msg[2], response);
			}
			else if(msg[1].equalsIgnoreCase("list")) {
				sendMessage(channel, botName + " is in the following channels: ");
				for(String s : getChannels())
					sendMessage(channel, s);
			}
			else if(msg[1].equalsIgnoreCase("df")) {
				if(msg.length == 3)
					channel = msg[2];

				_botSysCommd(channel,"df -h");
			}
			else if(msg[1].equalsIgnoreCase("uptime")) {
				if(msg.length == 3)
					channel = msg[2];

				_botSysCommd(channel,"uptime");
			}
			else if(msg[1].equalsIgnoreCase("date")) {
				if(msg.length == 3)
					channel = msg[2];

				_botSysCommd(channel,"date");
			}
		}
		
		// regular (public) functions
	}
	
	
	/**
	 * Performs a system command -- given to it by its parameter -- and returns
	 * all output in the form of a sendMessage to the given channel <i>or</i> 
	 * user.
	 * 
	 * @access	private
	 * @author	Adam Brenner <aebrenne@uci.edu>
	 * @since	3.0
	 */
	private void _botSysCommd(String channel, String command)
	{
		try {
			Scanner scan = new Scanner(Runtime.getRuntime().exec(command).getInputStream());
			
			while(scan.hasNextLine())
				sendMessage(channel,scan.nextLine());
			
			scan.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}