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

    public Bank(MethodContext ctx){
        super(ctx);
    }

    @Override
    public boolean activate()
    {
        return Walk.MovementArea.BANK_CHEST_AREA.getArea().contains(ctx.players.local());
    }

    @Override
    public void execute()
    {
        if(ctx.bank.isOpen())
        {
            ctx.bank.depositInventory();
            sleep(200, 500);
            ctx.bank.close();
        } else {
            ctx.bank.open();
        }
    }
}
