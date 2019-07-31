package com.zby;

import com.zby.main.GameFrame;
import com.zby.util.DateStore;

public class GameStart {

    public static void main(String[] args) {
         GameFrame gameFrame = new GameFrame() ;

        DateStore.put("gameFrame",gameFrame);
         gameFrame.init();
    }

}
