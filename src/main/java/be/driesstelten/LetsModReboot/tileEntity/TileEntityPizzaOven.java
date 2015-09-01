package be.driesstelten.LetsModReboot.tileEntity;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import akka.actor.dungeon.ChildrenContainer.TerminatingChildrenContainer;
import be.driesstelten.LetsModReboot.block.BlockPizzaOven_idle;
import be.driesstelten.LetsModReboot.block.PizzaOven;
import be.driesstelten.LetsModReboot.init.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPizzaOven extends TileEntity implements ISidedInventory {
	
	private static final int[] slotsTop = new int[]{ 0 };
	private static final int[] slotsBottom = new int [] { 2, 1 };
	private static final int[] slotsSides = new int[]{ 1 };
	
	private ItemStack[] ovenItemStacks = new ItemStack[1];
	
	public int ovenBurnTime;
	public int currentBurnTime;
	
	public int ovenCookTime;
	
	private String pizzaOvenName;
	
	public void pizzaOvenName(String string) {
		this.pizzaOvenName = string;
	}
	
	

	@Override
	public int getSizeInventory() {
		return this.ovenItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.ovenItemStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int taken) {
		if (this.ovenItemStacks[slot] != null) {
			ItemStack itemStack;
			if (this.ovenItemStacks[slot].stackSize <= taken) {
				itemStack = this.ovenItemStacks[slot];
				this.ovenItemStacks[slot] = null;
				return itemStack;
			} else {
				itemStack = this.ovenItemStacks[slot].splitStack(taken);
				if (this.ovenItemStacks[slot].stackSize == 0) {
					this.ovenItemStacks[slot] = null;
				}
				return itemStack;
			}
			
		} else {
			return null; 
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.ovenItemStacks[slot] != null) {
			ItemStack i = this.ovenItemStacks[slot];
			return i;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		this.ovenItemStacks[slot] = itemStack;
		
		if ((itemStack != null) && (itemStack.stackSize > getInventoryStackLimit())) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
		
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.pizzaOvenName : "Pizza Oven"
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.pizzaOvenName != null && this.pizzaOvenName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		NBTTagList tagList = tagCompound.getTagList("items", 10);
		this.ovenItemStacks = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tagCompound1 = tagList.getCompoundTagAt(i);
			byte b = tagCompound.getByte("slot");
			if (b >= 0 && b < this.ovenItemStacks.length) {
				this.ovenItemStacks[b] = ItemStack.loadItemStackFromNBT(tagCompound1);
			}
		}
		this.ovenBurnTime = tagCompound.getShort("burntime");
		this.ovenCookTime = tagCompound.getShort("cooktime");
		this.currentBurnTime = getItemBurnTime(this.ovenItemStacks[1]);
		
		if (tagCompound.hasKey("CustomName", 8)) {
			this.pizzaOvenName = tagCompound.getString("CustomName");
		}
		
	}
	
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		
		tagCompound.setShort("burntime", (short) this.ovenBurnTime);
		tagCompound.setShort("cooktime", (short) this.ovenCookTime);
		NBTTagList taglist = new NBTTagList();
		
		for (int i = 0; i < this.ovenItemStacks.length; i++) {
			if (this.ovenItemStacks[i] != null) {
				NBTTagCompound tagCompound1 = new NBTTagCompound();
				tagCompound.setByte("slot", (byte) i);
				this.ovenItemStacks[i].writeToNBT(tagCompound1);
				taglist.appendTag(tagCompound1);
			}
		}
		
		tagCompound.setTag("items", taglist);
		
		if (this.hasCustomInventoryName()) {
			tagCompound.setString("CustomName", this.pizzaOvenName);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1) {
		return this.ovenCookTime * par1 / 200;
	}
	
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1) {
		if (this.currentBurnTime == 0) {
			this.currentBurnTime = 200;
		}
		
		return this.ovenBurnTime * par1 / this.currentBurnTime;
	}
	
	public boolean isBurning() {
		return this.ovenBurnTime > 0;
	}
	
	public void updateEtity() {
		boolean flag = this.ovenBurnTime > 0;
		boolean flag1 = false;
		if (this.ovenBurnTime > 0) {
			this.ovenBurnTime--;
		}
		
		if (this.worldObj.isRemote) {
			if (this.ovenBurnTime == 0 && this.canSmelt()) {
				this.currentBurnTime = this.ovenBurnTime = getItemBurnTime(this.ovenItemStacks[1]);
				if (this.ovenBurnTime > 0) {
					flag1 = true;
					if (this.ovenItemStacks[1] != null) {
						this.ovenItemStacks[1].stackSize--;
						if (this.ovenItemStacks[1].stackSize == 0) {
							this.ovenItemStacks[1] = this.ovenItemStacks[1].getItem().getContainerItem(this.ovenItemStacks[1]);
						}
					}
				}
			}
			
			if (this.isBurning() && this.canSmelt()) {
				this.ovenCookTime++;
				if (this.ovenCookTime == 200) {
					this.ovenCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			} else {
				this.ovenCookTime = 0;
			}
		}
		
		if (flag != this.ovenBurnTime > 0) {
			flag1 = true;
			upd
		}
	}

}
