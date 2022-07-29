package youae;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.CommandExecutor;

public class Main extends JavaPlugin implements Listener,CommandExecutor { //명령어 처리 클래스는 CommandExecutor 인터페이스를 상속해야 한다.
	@Override
	   public void onEnable(){
	      Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "플러그인이 활성화 되었습니다.");
	      getServer().getPluginManager().registerEvents(this,this); //서버 켜질 때 무조건 넣어야 한다. 그래야 밑에 eventhandler 실행됨.
	      getCommand("다이아몬드블럭").setExecutor(this);
	      getCommand("test").setExecutor(this);
	      
	}
	
	@Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(sender instanceof Player){ //명령어를 사용자가 입력했으면
            Player player=(Player) sender; //명령어 사용자 객체를 플레이어 객체로 변환
            
            if (command.getName().equals("다이아몬드블럭")) {
	            player.sendMessage("아이템을 지급하였습니다.");
	            ItemStack item=new ItemStack(Material.DIAMOND_BLOCK); //item 만들기
	
	            item.setAmount(1); //item 1개로
	
	            player.getInventory().addItem(item); //item 지급
	            
	            return true;}
            
            else if (command.getName().equals("test")) {
	            player.sendMessage("아이템을 지급하였습니다.");
	            ItemStack item=new ItemStack(Material.IRON_BLOCK); //item 만들기
	           
	            item.setAmount(1); //item 1개로
	
	            player.getInventory().addItem(item); //item 지급
	            
	            player.getWorld().createExplosion(player.getLocation(),4);
	            return true;}
            
        }else if(sender instanceof ConsoleCommandSender){
            //콘솔창에서 사용한 경우
            sender.sendMessage("플레이어가 아닙니다.");
            return false;
        }
        return false;
    }
        
	   @Override
	   public void onDisable(){
	      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "플러그인이 비활성화 되었습니다.");
	   }
	   
	   @EventHandler
	   public void moving(PlayerMoveEvent a) { //내가 만드는 함수. 움직일때마다 실행하는 함수
		   Player p=a.getPlayer(); //Player라는 클래스를 사용하는데, a의 getplayer함수를 사용해서 등록
		   //p.sendMessage("너 움직임."); //개인메세지 보내줌
		   //p.sendTitle("할로", "Test", 10, 70, 20); //개인타이틀 보내줌
	   }
	   
	   @EventHandler
	   public void joined(PlayerJoinEvent a) {
		   Player p=a.getPlayer();
		   p.sendMessage("환영합니다..");
		   p.sendTitle(ChatColor.GREEN+"할로", "Test", 10, 70, 20);
	   }
	   
	   @EventHandler
	   public void breaking(BlockBreakEvent a) {
		   Player p=a.getPlayer();
		   p.sendMessage(ChatColor.RED+p.getName()+"가"+a.getBlock().getType()+"블럭을 부셨습니다.");
	   }
	   
	   @EventHandler
	   public void placing(BlockPlaceEvent a) {
		   Player p=a.getPlayer();
		   p.sendMessage(ChatColor.BLUE+p.getName()+"가"+a.getBlock().getType()+"블럭을 놓았다.");
		   if (a.getBlock().getType()==Material.DIAMOND_BLOCK) {
			   p.sendMessage(ChatColor.RED+"그 곳에 블럭을 두면 안됩니다.");
			   a.setCancelled(true);// //해당하는 이벤트를 취소시킨다.(블럭 삭제)
		   }
	   
	   }
}
