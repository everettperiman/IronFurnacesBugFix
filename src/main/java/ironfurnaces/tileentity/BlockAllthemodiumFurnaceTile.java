package ironfurnaces.tileentity;

import ironfurnaces.Config;
import ironfurnaces.container.BlockAllthemodiumFurnaceContainer;
import ironfurnaces.init.Registration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;

public class BlockAllthemodiumFurnaceTile extends BlockIronFurnaceTileBase {
    public BlockAllthemodiumFurnaceTile() {
        super(Registration.ALLTHEMODIUM_FURNACE_TILE.get());
    }

    @Override
    protected int getCookTimeConfig()
    {
        int i = Config.allthemodiumFurnaceSpeed.get();
        int j = this.recipeType != null && this.recipeType != IRecipeType.SMELTING ? 2 : 1;
        if (this.world != null)
        {
            int k = this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>)this.recipeType, this, this.world).map(AbstractCookingRecipe::getCookTime).orElse((i / j));
            if (this.recipeType != null && k < (i / j))
            {
                return k;
            }
        }
        return i / j;
    }



    @Override
    public String IgetName() {
        return "container.allthemodium_furnace";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BlockAllthemodiumFurnaceContainer(i, world, pos, playerInventory, playerEntity, this.fields);
    }

}
