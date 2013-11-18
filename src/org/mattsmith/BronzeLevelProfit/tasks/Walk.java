package org.mattsmith.BronzeLevelProfit.tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Area;
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

    public static enum MovementArea {

        MINE_AREA(new Area(new Tile[] { new Tile(3229, 3153, 0), new Tile(3228, 3153, 0), new Tile(3227, 3152, 0),
                new Tile(3226, 3152, 0), new Tile(3225, 3152, 0), new Tile(3224, 3151, 0),
                new Tile(3223, 3151, 0), new Tile(3222, 3150, 0), new Tile(3222, 3149, 0),
                new Tile(3221, 3148, 0), new Tile(3221, 3147, 0), new Tile(3222, 3147, 0),
                new Tile(3222, 3146, 0), new Tile(3223, 3145, 0), new Tile(3224, 3144, 0),
                new Tile(3225, 3144, 0), new Tile(3226, 3143, 0), new Tile(3227, 3143, 0),
                new Tile(3228, 3143, 0), new Tile(3229, 3144, 0), new Tile(3230, 3144, 0),
                new Tile(3231, 3144, 0), new Tile(3231, 3145, 0), new Tile(3232, 3145, 0),
                new Tile(3233, 3146, 0), new Tile(3233, 3147, 0), new Tile(3234, 3148, 0),
                new Tile(3234, 3149, 0), new Tile(3235, 3150, 0), new Tile(3235, 3151, 0),
                new Tile(3235, 3152, 0), new Tile(3234, 3154, 0), new Tile(3233, 3154, 0),
                new Tile(3232, 3155, 0), new Tile(3231, 3155, 0), new Tile(3231, 3154, 0) })),

        FURNACE_AREA(new Area(new Tile[] { new Tile(3221, 3258, 0), new Tile(3226, 3258, 0), new Tile(3230, 3255, 0),
                new Tile(3229, 3250, 0), new Tile(3224, 3251, 0), new Tile(3220, 3253, 0) })),

        BANK_CHEST_AREA(new Area(new Tile[] { new Tile(3212, 3258, 0), new Tile(3213, 3258, 0), new Tile(3214, 3258, 0),
                new Tile(3215, 3258, 0), new Tile(3216, 3258, 0), new Tile(3216, 3257, 0),
                new Tile(3216, 3256, 0), new Tile(3215, 3255, 0), new Tile(3215, 3254, 0),
                new Tile(3214, 3254, 0), new Tile(3213, 3254, 0), new Tile(3212, 3255, 0),
                new Tile(3211, 3255, 0), new Tile(3211, 3256, 0), new Tile(3211, 3257, 0)}));

        private Area area;
        private MovementArea(Area area) {
            this.area = area;
        }

        public Area getArea() {
            return area;
        }
    }

    public static enum MovementPath {

        LUMBRIDGE_MINE_TO_FURNACE(new Tile[] { new Tile(3231, 3150, 0), new Tile(3235, 3153, 0), new Tile(3237, 3157, 0),
                new Tile(3239, 3162, 0), new Tile(3241, 3167, 0), new Tile(3242, 3172, 0),
                new Tile(3243, 3177, 0), new Tile(3243, 3182, 0), new Tile(3244, 3187, 0),
                new Tile(3243, 3192, 0), new Tile(3243, 3198, 0), new Tile(3239, 3201, 0),
                new Tile(3235, 3205, 0), new Tile(3234, 3209, 0), new Tile(3234, 3215, 0),
                new Tile(3234, 3220, 0), new Tile(3234, 3225, 0), new Tile(3234, 3230, 0),
                new Tile(3230, 3233, 0), new Tile(3225, 3235, 0), new Tile(3224, 3240, 0),
                new Tile(3222, 3245, 0), new Tile(3220, 3250, 0), new Tile(3225, 3252, 0),
                new Tile(3224, 3255, 0)}),

        LUMBRIDGE_FURNACE_TO_BANK(new Tile[] { new Tile(3224, 3255, 0), new Tile(3219, 3255, 0), new Tile(3214, 3256, 0) }),

        LUMBRIDGE_BANK_TO_MINE(new Tile[] { new Tile(3216, 3256, 0), new Tile(3218, 3251, 0), new Tile(3220, 3247, 0),
                new Tile(3222, 3242, 0), new Tile(3223, 3237, 0), new Tile(3226, 3233, 0),
                new Tile(3230, 3230, 0), new Tile(3233, 3226, 0), new Tile(3236, 3221, 0),
                new Tile(3237, 3216, 0), new Tile(3236, 3211, 0), new Tile(3236, 3206, 0),
                new Tile(3236, 3201, 0), new Tile(3241, 3200, 0), new Tile(3242, 3195, 0),
                new Tile(3243, 3190, 0), new Tile(3242, 3185, 0), new Tile(3240, 3180, 0),
                new Tile(3240, 3175, 0), new Tile(3241, 3170, 0), new Tile(3240, 3165, 0),
                new Tile(3239, 3160, 0), new Tile(3236, 3156, 0), new Tile(3231, 3154, 0),
                new Tile(3230, 3149, 0)}),

        LUMBRIDGE_BANK_TO_GE(new Tile[] { new Tile(3214, 3257, 0), new Tile(3217, 3261, 0), new Tile(3218, 3265, 0),
                new Tile(3216, 3270, 0), new Tile(3216, 3276, 0), new Tile(3212, 3279, 0),
                new Tile(3208, 3280, 0), new Tile(3202, 3280, 0), new Tile(3197, 3280, 0),
                new Tile(3192, 3282, 0), new Tile(3188, 3284, 0), new Tile(3183, 3285, 0),
                new Tile(3178, 3286, 0), new Tile(3172, 3286, 0), new Tile(3167, 3288, 0),
                new Tile(3166, 3293, 0), new Tile(3166, 3298, 0), new Tile(3167, 3303, 0),
                new Tile(3171, 3306, 0), new Tile(3175, 3309, 0), new Tile(3177, 3314, 0),
                new Tile(3178, 3319, 0), new Tile(3178, 3324, 0), new Tile(3177, 3330, 0),
                new Tile(3177, 3335, 0), new Tile(3177, 3340, 0), new Tile(3177, 3346, 0),
                new Tile(3177, 3351, 0), new Tile(3177, 3356, 0), new Tile(3177, 3361, 0),
                new Tile(3174, 3365, 0), new Tile(3170, 3369, 0), new Tile(3169, 3373, 0),
                new Tile(3168, 3379, 0), new Tile(3168, 3384, 0), new Tile(3169, 3389, 0),
                new Tile(3170, 3394, 0), new Tile(3171, 3399, 0), new Tile(3172, 3404, 0),
                new Tile(3172, 3409, 0), new Tile(3172, 3415, 0), new Tile(3172, 3420, 0),
                new Tile(3172, 3425, 0), new Tile(3174, 3430, 0), new Tile(3174, 3435, 0),
                new Tile(3174, 3440, 0), new Tile(3174, 3446, 0), new Tile(3174, 3447, 0),
                new Tile(3173, 3452, 0), new Tile(3171, 3457, 0), new Tile(3168, 3461, 0),
                new Tile(3165, 3466, 0), new Tile(3164, 3471, 0), new Tile(3164, 3476, 0),
                new Tile(3168, 3478, 0), new Tile(3173, 3476, 0)}),

        LUMBRIDGE_GE_TO_BANK(new Tile[] {} );

        private Tile[] path;
        private MovementPath(Tile[] path){
            this.path = path;
        }

        public Tile[] getPath() {
            return path;
        }
    }

    public static Tile[] reverseArray(Tile[] data)
    {
        Tile[] reversedData = new Tile[data.length];
        int j = data.length - 1;
        for(int i=0; i < data.length; i++)
        {
            reversedData[i] = data[j--];
        }
        return reversedData;
    }

    private MovementPath Path;

    public Walk(MethodContext ctx, MovementPath path){
        super(ctx);
        this.Path = path;
    }

    @Override
    public boolean activate()
    {
        int BackpackSize = ctx.backpack.select().count();

        if(Path == MovementPath.LUMBRIDGE_MINE_TO_FURNACE)
        {
            if(BackpackSize < 28 || MovementArea.FURNACE_AREA.getArea().contains(ctx.players.local())) {
                return false;
            }
            return true;
        }

        if(Path == MovementPath.LUMBRIDGE_BANK_TO_MINE)
        {
            if(BackpackSize > 0 || MovementArea.MINE_AREA.getArea().contains(ctx.players.local())) {
                return false;
            }
            return true;
        }

        if(Path == MovementPath.LUMBRIDGE_FURNACE_TO_BANK)
        {
            if(MovementArea.FURNACE_AREA.getArea().contains(ctx.players.local()))
            {
                boolean CopperCount = ctx.backpack.select().id(Mine.COPPER_ORE).size() == 0;
                boolean TinCount = ctx.backpack.select().id(Mine.TIN_ORE).size() == 0;
                boolean BronzeCount = ctx.backpack.select().id(Mine.BRONZE_BAR).size() > 0;

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
        System.out.println(Path.name());

        if (!ctx.movement.isRunning() && ctx.movement.getEnergyLevel() > 30){
            ctx.movement.setRunning(true);
        }
        TilePath path = new TilePath(ctx, Path.getPath());
        path.traverse();
        sleep(5000, 10000);
    }
}
