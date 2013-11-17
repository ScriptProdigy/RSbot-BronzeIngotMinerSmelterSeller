package tasks;

import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.methods.MethodContext;

/**
 * Created with IntelliJ IDEA.
 * All legal rights belong to the user below unless stated otherwise.
 * User: Matthew
 * Date: 11/14/13
 * Time: 6:11 PM
 */
public class Furnace extends Task {

    public Furnace(MethodContext ctx){
        super(ctx);
    }

    @Override
    public boolean activate()
    {
        return Constants.FURNACE_AREA.contains(ctx.players.local()) &&
            ctx.backpack.id(Constants.COPPER_ORE).size() > 0 &&
            ctx.backpack.id(Constants.TIN_ORE).size() > 0;
    }

    @Override
    public void execute()
    {
        GameObject furnace = ctx.objects.select().id(Constants.FURNACE).nearest().first().poll();
        furnace.interact("Smelt");
        sleep(2000, 2300);

        Component Smelt = ctx.widgets.get(1371, 5);
        Smelt.click();
        sleep(Random.nextInt(34000, 37000));
    }
}
