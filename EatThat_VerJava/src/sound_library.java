import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

class sound_library{
	static AudioClip dogbarking;
	static AudioClip hungry;
	static AudioClip pangpare;
	static AudioClip caution;
	static AudioClip eating;
	static AudioClip winner;
	static AudioClip loser;
	static AudioClip menu;
	static AudioClip bgm1;
	static AudioClip bgm2;
	static AudioClip bgm3;
	static AudioClip bgm4;
	
	static{
		try{
			dogbarking=Applet.newAudioClip(new URL("file:./sound/Dogbarking.wav"));
			hungry=Applet.newAudioClip(new URL("file:./sound/hungry.wav"));
			pangpare=Applet.newAudioClip(new URL("file:./sound/pangpare.wav"));
			caution=Applet.newAudioClip(new URL("file:./sound/caution.wav"));
			eating=Applet.newAudioClip(new URL("file:./sound/eating.wav"));
			winner=Applet.newAudioClip(new URL("file:./sound/Winner_BGM.wav"));
			loser=Applet.newAudioClip(new URL("file:./sound/Loser_BGM.wav"));
			menu=Applet.newAudioClip(new URL("file:./sound/menu_bgm.wav"));
			bgm1=Applet.newAudioClip(new URL("file:./sound/play_bgm1.wav"));
			bgm2=Applet.newAudioClip(new URL("file:./sound/play_bgm2.wav"));
			bgm3=Applet.newAudioClip(new URL("file:./sound/play_bgm3.wav"));
			bgm4=Applet.newAudioClip(new URL("file:./sound/play_bgm4.wav"));
		}catch(Exception e){}
	}
	
	public static void dogbarking_play(){ dogbarking.play(); }
	public static void hungry_play(){ hungry.play(); }
	public static void pangpare_play(){ pangpare.play(); }
	public static void caution_play(){ caution.play(); }
	public static void eating_play(){ eating.play(); }
	public static void winner_play(){ winner.play(); }
	public static void winner_stop(){ winner.stop(); }
	public static void loser_play(){ loser.play(); }
	public static void loser_stop(){ loser.stop(); }
	public static void menu_loop(){ menu.loop(); }
	public static void menu_stop(){ menu.stop(); }
	public static void bgm1_loop(){ bgm1.loop(); }
	public static void bgm1_stop(){ bgm1.stop(); }
	public static void bgm2_loop(){ bgm2.loop(); }
	public static void bgm2_stop(){ bgm2.stop(); }
	public static void bgm3_loop(){ bgm3.loop(); }
	public static void bgm3_stop(){ bgm3.stop(); }
	public static void bgm4_loop(){ bgm4.loop(); }
	public static void bgm4_stop(){ bgm4.stop(); }




}