package org.mattsmith.BronzeLevelProfit;

import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;
import org.mattsmith.BronzeLevelProfit.tasks.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * All legal rights belong to the user below unless stated otherwise.
 * User: Matthew
 * Date: 11/14/13
 * Time: 5:44 PM
 */
@Manifest(name = "MattSmiths Profit Miner", authors = "MattSmith", description = "Mine shit hell yea money oh hell yea")
public class MinerProfiting extends PollingScript {

    private final LinkedList<Task> BotProcesses = new LinkedList<Task>();
    private final LinkedList<Task> GrandExchangeProcesses = new LinkedList<Task>();
    private int BankedCount = 0;

    public MinerProfiting()
    {
        BotProcesses.add(new Walk(ctx, Walk.MovementPath.LUMBRIDGE_BANK_TO_MINE));
        BotProcesses.add(new Mine(ctx));
        BotProcesses.add(new Walk(ctx, Walk.MovementPath.LUMBRIDGE_MINE_TO_FURNACE));
        BotProcesses.add(new Furnace(ctx));
        BotProcesses.add(new Walk(ctx, Walk.MovementPath.LUMBRIDGE_FURNACE_TO_BANK));
        BotProcesses.add(new Bank(ctx));

        GrandExchangeProcesses.add(new Walk(ctx, Walk.MovementPath.LUMBRIDGE_BANK_TO_GE));
        //TODO:  Implement Exchanging..
        GrandExchangeProcesses.add(new Walk(ctx, Walk.MovementPath.LUMBRIDGE_GE_TO_BANK));
    }

    @Override
    public void start() {
        //TODO:  PathFind my way to the mine at start
        //TODO:  Check to see if backpack is empty at start, if not empty at bank then start BotProcesses

        System.out.println("Script started");
    }


    // Returns 1 if it successfully activated the last process of the bot.
    public int actProcessList(LinkedList<Task> list) {
        for(final Task task : list)
        {
            if(task.activate()) {
                task.execute();
                System.out.println(Random.nextInt(0,100));
                if(task == list.getLast())
                    return 1;
            }
        }
        return 0;
    }

    @Override
    public int poll()
    {
        if(BankedCount != 0 && (BankedCount%5) == 0) {
            if(actProcessList(GrandExchangeProcesses) == 1) {
                BankedCount = 0;
            }
        } else {
            if(actProcessList(BotProcesses) == 1) {
                BankedCount += 1;
            }
        }

        return 0;
    }

}
