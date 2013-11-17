package org.mattsmith.BronzeLevelProfit.tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

/**
 * Created with IntelliJ IDEA.
 * All legal rights belong to the user below unless stated otherwise.
 * User: Matthew
 * Date: 11/13/13
 * Time: 6:58 PM
 */
public abstract class Task extends MethodProvider{
    public Task(MethodContext ctx) {
        super(ctx);
    }

    public abstract boolean activate();
    public abstract void execute();
}
