package factoriaetsia.com.map;


import com.google.android.maps.MapView;

import android.content.Context;
import android.util.AttributeSet;

public class MapLocationViewer extends MapView {

	private BubblesOverlay overlay;

	public MapLocationsManager getManager() {
    	return overlay.getManager();
    }
    
    public MapLocationViewer(Context context, String apiKey) {
		super(context, apiKey);
		init();
	}
	
	public MapLocationViewer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {		
		overlay = new BubblesOverlay();
    	getOverlays().add(overlay);
    	
    	setClickable(true);

	}
	
}