package com.asteria.engine.net.packet.impl;


import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import com.asteria.Main;
import com.asteria.engine.net.ProtocolBuffer;
import com.asteria.engine.net.packet.PacketDecoder;
import com.asteria.engine.net.packet.PacketOpcodeHeader;
import com.asteria.world.entity.UpdateFlags.Flag;
import com.asteria.world.entity.player.Player;

/**
 * Sent when the player speaks.
 * 
 * @author lare96
 */
@PacketOpcodeHeader({ 4 })
public class DecodeChatPacket extends PacketDecoder {


    private static Logger logger = Logger.getLogger(Main.class.getSimpleName());
    @Override
    public void decode(Player player, ProtocolBuffer buf) {
    	
        int effects = buf.readByte(false, ProtocolBuffer.ValueType.S);
        int color = buf.readByte(false, ProtocolBuffer.ValueType.S);
        int chatLength = (player.getSession().getPacketLength() - 2);
        byte[] text = buf.readBytesReverse(chatLength,
                ProtocolBuffer.ValueType.A);

        if (effects < 0 || color < 0 || chatLength < 0 || text == null) {
            return;
        }

        String stringtext = null;
		try {
			stringtext = new String(text, "UTF-8");
	        logger.info(stringtext);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stringtext = null;
        player.setChatEffects(effects);
        player.setChatColor(color);
        player.setChatText(text);
        player.getFlags().flag(Flag.CHAT);
    }
}
