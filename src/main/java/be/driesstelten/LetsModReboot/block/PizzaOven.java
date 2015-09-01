package be.driesstelten.LetsModReboot.block;

import java.util.Random;

import com.sun.org.apache.xpath.internal.operations.Mod;

import be.driesstelten.LetsModReboot.LetsModReboot;
import be.driesstelten.LetsModReboot.init.ModBlocks;
import be.driesstelten.LetsModReboot.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PizzaOven extends BlockContainerLMRB {
	
	@SideOnly(Side.CLIENT)
	private IIcon top;
	@SideOnly(Side.SERVER)
	private IIcon front;
	
	private boolean isBurning;
	private boolean isBurning2;
	private Random r = new Random();
	
	public PizzaOven(boolean isActive) {
		super();
		isBurning2 = isActive;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister IconRegistry) {
		this.blockIcon = IconRegistry.registerIcon(Reference.MOD_ID + ":pizzaOven_side");
		this.front = IconRegistry.registerIcon(this.isBurning2 ? Reference.MOD_ID + ":pizzaOven_front_on" : Reference.MOD_ID + ":pizzaOven_front_off");
		this.top = IconRegistry.registerIcon(Reference.MOD_ID + ":pizzaOven_top");
	}
	
	public IIcon getIcon(int side, int meta) {
		if (side == 1) {
			return top;
		} else if (side == 3) {
			return front;
		} else {
			return this.blockIcon;
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		player.openGui(LetsModReboot.instance, 0, world, x, y, z);
		return true;
	}
	
	public Item itemDropped(int par1, Random random, int par3) {
		return Item.getItemFromBlock(ModBlocks.pizzaOven_idle);
	}
	
	public Item getItem(World world, int par2, int par3, int par4) {
		return Item.getItemFromBlock(ModBlocks.pizzaOven_idle);
	}
	
	@SideOnly(Side.CLIENT)
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.direction(world, x, y, z);
	}

	private void direction(World world, int x, int y, int z) {
		if(!world.isRemote) {
			Block direction = world.getBlock(x, y, z - 1);
			Block direction1 = world.getBlock(x, y, z + 1);
			Block direction2 = world.getBlock(x - 1, y, z);
			Block direction3 = world.getBlock(x + 1, y, z);
			
			byte b = 3;
			
			if (func_149730_j() && direction.func_149730_j()) {
				b = 3;
			}
			if (func_149730_j() && direction1.func_149730_j()) {
				b = 2;
			}
			if (func_149730_j() && direction2.func_149730_j()) {
				b = 5;
			}
			if (func_149730_j() && direction3.func_149730_j()) {
				b = 4;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, b, 2);
		}
		
	}
	
	public void onBlockPlacedBy(World world, int x, int y , int z, EntityLivingBase entity, ItemStack itemstack) {
		int direction = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		switch (direction) {
		case 0:
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			break;
		case 1:
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
			break;
		case 2:
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
			break;
		case 3:
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
			break;
		default:
			break;
		}
		
		if (itemstack.hasDisplayName()) {
			((TileEntityPizzaOven) world.getTileEntity(x, y, z)).pizzaOvenName(itemstack.getDisplayName());
		}
	}
	
	public void updateBlockState(boolean burning, World world, int x, int y, int z) {
		int direction = world.getBlockMetadata(x, y, z);
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		isBurning = true;
		
		if (burning) {
			world.setBlock(x, y, z, ModBlocks.pizzaOven_active);
		} else {
			world.setBlock(x, y, z, ModBlocks.pizzaOven_idle);
		}
		
		isBurning = false;
		
		world.setBlockMetadataWithNotify(x, y, z, direction, 2);
		
		if (tileEntity != null) {
			tileEntity.validate();
			world.setTileEntity(x, y, z, tileEntity);
		}
		
	}
	
	public void breakBlock (World world, int x, int y, int z, Block block, int meta) {
		if (!isBurning) {
			TileEntityPizzaOven tileEntityPizzaOven = (TileEntityPizzaOven) world.getTileEntity(x, y, z);
			
			if (tileEntityPizzaOven != null) {
				for (int i = 0; i < tileEntityPizzaOven.getInventorySize; i++) {
					ItemStack itemStack = tileEntityPizzaOven.getStackInSlot(1);
					if (itemStack != null) {
						float f = this.r.nextFloat() * 0.6F + 0.1F;
						float f1 = this.r.nextFloat() * 0.6F + 0.1F;
						float f2 = this.r.nextFloat() * 0.6F + 0.1F;
						
						while (itemStack.stackSize > 0) {
							int j = this.r.nextInt(21) + 10;
							
							if (j < itemStack.stackSize) {
								j = itemStack.stackSize;
							}
							
							itemStack.stackSize -= j;
							EntityItem entityItem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));
							
							if (itemStack.hasTagCompound()) {
								entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
							}
							
							float f3 = 0.02F;
							entityItem.motionX = (double) ((float) this.r.nextGaussian() * f3);
							entityItem.motionY = (double) ((float) this.r.nextGaussian() * f3 + 0.1F);
							entityItem.motionZ = (double) ((float) this.r.nextGaussian() * f3);
							
							world.spawnEntityInWorld(entityItem);

						}

					}
				}
				
				world.func_147453_f(x, y, z, block);
				
			}
		}
		
		super.breakBlock(world, x, y, z, block, meta);
		
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (isBurning2) {
			int direction = world.getBlockMetadata(x, y, z);
			float xx = (float) x + 0.5F, yy  = (float) random.nextFloat() * 6.0F / 16.0F , zz  = (float) z + 0.5F, xx2 = random.nextFloat() * 0.3F - 0.2F, zz2 = 0.5F;
			
			if (direction == 4) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			} else if (direction == 5) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			} else if (direction == 3) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			} else if (direction == 2) {
				world.spawnParticle("smoke", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
				world.spawnParticle("flame", (double) (xx - zz2), (double) yy, (double) (zz + xx2), 0.0F, 0.0F, 0.0F);
			}
			
		}
	}

}
