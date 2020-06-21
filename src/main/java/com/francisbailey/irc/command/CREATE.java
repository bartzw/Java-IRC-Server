package com.francisbailey.irc.command;

import com.francisbailey.irc.*;
import com.francisbailey.irc.message.ClientMessage;
import com.francisbailey.irc.message.ServerMessage;
import com.francisbailey.irc.message.ServerMessageBuilder;

public class CREATE implements Executable {
    @Override
    public void execute(Connection connection, ClientMessage clientMessage, ServerManager server) {

        String channel = clientMessage.getParameter(0);
        String topic = clientMessage.getParameter(1);
        ChannelManager channelManager = server.getChannelManager();
        String nick = connection.getClientInfo().getNick();

        if (!channelManager.hasChannel(channel)) {
            channelManager.addChannel(channel, topic);
            connection.send(ServerMessageBuilder
                    .from(server.getName())
                    .withReplyCode(ServerMessage.ERR_NOSUCHCHANNEL)
                    .andMessage(nick + " " + channel + " :channel created!")
                    .build()
            );
        }
    }

    @Override
    public int getMinimumParams() {
        return 2;
    }

    @Override
    public boolean canExecuteUnregistered() {
        return false;
    }
}
