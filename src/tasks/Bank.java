package tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.GameObject;

/**
 * Created with IntelliJ IDEA.
 * All legal rights belong to the user below unless stated otherwise.
 * User: Matthew
 * Date: 11/14/13
 * Time: 6:11 PM
 */
public class Bank extends Task {

    public Bank(MethodContext ctx){
        super(ctx);
    }

    @Override
    public boolean activate()
    {
        System.out.print("Bank Contains Player: " );
        System.out.println(Constants.BANK_CHEST_AREA.contains(ctx.players.local()));
        return Constants.BANK_CHEST_AREA.contains(ctx.players.local()) &&
                ctx.backpack.size() > 0;
    }

    @Override
    public void execute()
    {
        GameObject chest = ctx.objects.select().id(Constants.BANK_CHEST).poll();
        chest.interact("Use");

        ctx.bank.depositInventory();
        //Component SelectBronze = ctx.widgets.get(762, 54).getChild(0);
        //SelectBronze.interact("Deposit-All");
    }
}
