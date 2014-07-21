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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;


public class WorldBot {
	
	private final static String botName      = "WorldBot";
	private final static String ircAddress   = "irc.kbve.com";
	private final static String nickServPass = "password";
	private final static String botAdmin     = "h0lybyte";
	private final static String botAdminChan = "h0lybyte";
	private final static String botTrigWord  = "!bot";
	private final static int ircPort         = 6667;
	private final static int socketPort      = 63731;
	public static IRC bot = null;
	

	public static void RunWorldBot() {
		
		bot = new IRC(botName, ircAddress, nickServPass, botAdmin, botAdminChan, botTrigWord, ircPort);
		try {
			if(!bot.isConnected())
				{
				bot.connect(ircAddress, ircPort);
				bot.setAutoNickChange(true);
				}
		} catch (NickAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SSLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bot.joinChannel("#kbve");
		
	}

}