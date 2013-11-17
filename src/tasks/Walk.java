package tasks;

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
        if(Constants.FURNACE_AREA.contains(ctx.players.local()) &&
                ctx.backpack.id(Constants.COPPER_ORE).size() > 0 &&
                ctx.backpack.id(Constants.TIN_ORE).size() > 0) {
            return false;
        }

        if(Constants.MINE_AREA.contains(ctx.players.local())
                && ctx.backpack.count() < 28) {
            return false;
        }

        if(Constants.BANK_CHEST_AREA.contains(ctx.players.local())
                && ctx.backpack.count() > 0) {
            return false;
        }
        return true;

        /*Boolean canWalk = ctx.players.local().getAnimation() == -1;

        System.out.print("BackPack Size: ");
        System.out.println(ctx.backpack.select().count());

        if(Path == Constants.LUMBRIDGE_BANK_TO_MINE) {
            canWalk = canWalk && ctx.backpack.select().count() == 0;
            return canWalk;
        }

        if(Path == Constants.LUMBRIDGE_MINE_TO_FURNACE) {
            canWalk = canWalk && ctx.backpack.select().count() == 28;
            return canWalk;
        }

        if(Path == Constants.LUMBRIDGE_FURNACE_TO_BANK) {
            canWalk = canWalk && ctx.backpack.select().count() == 14;
            return canWalk;
        }

        return false;  */
    }

    @Override
    public void execute()
    {
        System.out.println("Walking..");
        if (!ctx.movement.isRunning() && ctx.movement.getEnergyLevel() > 30){
            ctx.movement.setRunning(true);
        }
        TilePath path = new TilePath(ctx, Path);
        path.traverse();
        sleep(5000, 10000);
    }
}
