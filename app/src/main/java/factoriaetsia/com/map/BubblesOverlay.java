package factoriaetsia.com.map;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class BubblesOverlay  extends Overlay {
    
    private MapLocationsManager mManager;  
    
    public MapLocationsManager getManager() {
		return mManager;
    }
        
	public BubblesOverlay() {
	
		mManager = new MapLocationsManager();
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {		
		return mManager.verifyHitMapLocation(mapView, event);
	}
	
    public void draw (Canvas canvas, MapView mapView, boolean shadow) {		
    	mManager.draw(canvas, mapView, shadow);
    }


    
}