package org.mattsmith.BronzeLevelProfit.tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;

/**
 * Created with IntelliJ IDEA.
 * All legal rights belong to the user below unless stated otherwise.
 * User: Matthew
 * Date: 11/14/13
 * Time: 6:11 PM
 */
public class Bank extends Task {

    private int BANK_CHEST = 79036;

    public Bank(MethodContext ctx){
        super(ctx);
    }

    @Override
    public boolean activate()
    {
        System.out.print("Bank Contains Player: " );
        System.out.println(Walk.Movement_Area.BANK_CHEST_AREA.getArea().contains(ctx.players.local()));
        return Walk.Movement_Area.BANK_CHEST_AREA.getArea().contains(ctx.players.local());
    }

    @Override
    public void execute()
    {
        GameObject chest = ctx.objects.select().id(BANK_CHEST).poll();
        chest.interact("Use");

        ctx.bank.depositInventory();
        //Component SelectBronze = ctx.widgets.get(762, 54).getChild(0);
        //SelectBronze.interact("Deposit-All");
    }
}
