package org.mattsmith.BronzeLevelProfit.tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;

/**
 * Created with IntelliJ IDEA.
 * All legal rights belong to the user below unless stated otherwise.
 * User: Matthew
 * Date: 11/14/13
 * Time: 6:11 PM
 */
public class Walk extends Task {

    private Tile[] Path;

    public Walk(MethodContext ctx, Tile[] path){
        super(ctx);
        this.Path = path;
    }

    @Override
    public boolean activate()
    {
        int BackpackSize = ctx.backpack.select().count();

        System.out.print("Backpack Size: ");
        System.out.println(BackpackSize);

        if(Path == Constants.LUMBRIDGE_MINE_TO_FURNACE)
        {
            System.out.println("Constants.LUMBRIDGE_MINE_TO_FURNACE");
            if(BackpackSize < 28 || Constants.FURNACE_AREA.contains(ctx.players.local())) {
                return false;
            }
            return true;
        }

        if(Path == Constants.LUMBRIDGE_BANK_TO_MINE)
        {
            System.out.println("Constants.LUMBRIDGE_BANK_TO_MINE");
            if(BackpackSize > 0 || Constants.MINE_AREA.contains(ctx.players.local())) {
                return false;
            }
            return true;
        }

        if(Path == Constants.LUMBRIDGE_FURNACE_TO_BANK)
        {
            System.out.println("Constants.LUMBRIDGE_FURNACE_TO_BANK");
            if(Constants.FURNACE_AREA.contains(ctx.players.local()))
            {
                boolean CopperCount = ctx.backpack.select().id(Constants.COPPER_ORE).size() == 0;
                boolean TinCount = ctx.backpack.select().id(Constants.TIN_ORE).size() == 0;
                boolean BronzeCount = ctx.backpack.select().id(Constants.BRONZE_BAR).size() > 0;

                return CopperCount && TinCount && BronzeCount;
            }
            return false;
        }

        return false;
    }

    @Override
    public void execute()
    {
        System.out.print("Walking.. ");
        System.out.println(Path);
        if (!ctx.movement.isRunning() && ctx.movement.getEnergyLevel() > 30){
            ctx.movement.setRunning(true);
        }
        TilePath path = new TilePath(ctx, Path);
        path.traverse();
        sleep(5000, 10000);
    }
}
