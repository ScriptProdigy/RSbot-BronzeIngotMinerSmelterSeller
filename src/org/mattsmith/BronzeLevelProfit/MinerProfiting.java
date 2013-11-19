package org.mattsmith.BronzeLevelProfit;

import Overflow.pathfinder.core.util.Structure;
import Overflow.pathfinder.impl.Pathfinder;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;
import org.mattsmith.BronzeLevelProfit.tasks.*;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;

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
    private boolean MovedToStartPosition = false;

    private Pathfinder pathToStart = new Pathfinder();
    private TilePath runToStart = null;

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

    private Tile toTile(int hash) {
        return new Tile(Structure.TILE.getX(hash), Structure.TILE.getY(hash), Structure.TILE.getZ(hash));
    }

    private org.powerbot.script.wrappers.TilePath toPath(final Overflow.pathfinder.core.wrapper.TilePath tp)
    {
        Tile[] arr = new Tile[tp.size()];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = toTile(tp.get(i).getHash());
        }
        return ctx.movement.newTilePath(arr);
    }

    @Override
    public int poll()
    {
        if(MovedToStartPosition == false)
        {
            System.out.println("Walking to start.. ");
            Tile pLocation = ctx.players.local().getLocation();

            if(!Walk.MovementArea.BANK_CHEST_AREA.getArea().contains(ctx.players.local()))
            {
                if(runToStart == null) {
                    runToStart = toPath(pathToStart.findPath(Structure.TILE.getHash(pLocation.x, pLocation.y, pLocation.plane), Structure.TILE.getHash(3214, 3257, 0), 500, false));
                }

                runToStart.traverse();
                sleep(Random.nextInt(1000,2000));

            } else {
                System.out.println("At start! Beginning.. ");
                MovedToStartPosition = true;
            }
        } else {
            if(BankedCount != 0 && (BankedCount%5) == 0) {
                if(actProcessList(GrandExchangeProcesses) == 1) {
                    BankedCount = 0;
                }
            } else {
                if(actProcessList(BotProcesses) == 1) {
                    BankedCount += 1;
                }
            }
        }
        return 0;
    }

}
