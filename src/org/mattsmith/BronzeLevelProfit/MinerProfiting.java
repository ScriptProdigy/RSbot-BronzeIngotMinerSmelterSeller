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

    private final LinkedList<LinkedList<Task>> BotProcesses = new LinkedList();

    public MinerProfiting()
    {
        LinkedList<Task> MiningAndSmelting = new LinkedList();
        MiningAndSmelting.add(new Walk(ctx, Walk.MovementPath.LUMBRIDGE_BANK_TO_MINE));
        MiningAndSmelting.add(new Mine(ctx));
        MiningAndSmelting.add(new Walk(ctx, Walk.MovementPath.LUMBRIDGE_MINE_TO_FURNACE));
        MiningAndSmelting.add(new Furnace(ctx));
        MiningAndSmelting.add(new Walk(ctx, Walk.MovementPath.LUMBRIDGE_FURNACE_TO_BANK));
        MiningAndSmelting.add(new Bank(ctx));

        LinkedList<Task> GrandExchangeSelling = new LinkedList();
        //TODO:  Implement walking to and back from GrandExchange, as well as Exchanging..

        BotProcesses.add(MiningAndSmelting);
        BotProcesses.add(GrandExchangeSelling);
    }

    @Override
    public void start() {
        //TODO:  PathFind my way to the mine at start
        //TODO:  Check to see if backpack is empty at start, if not empty at bank then start BotProcesses

        System.out.println("Script started");
    }

    @Override
    public int poll() {
        for(final LinkedList<Task> BotProcess : BotProcesses)
        {
            for(final Task task : BotProcess)
            {
                if(task.activate()) {
                    task.execute();
                    System.out.println(Random.nextInt(0,100));
                    return Random.nextInt(198, 472);
                }
            }
        }
        return 0;
    }

}
