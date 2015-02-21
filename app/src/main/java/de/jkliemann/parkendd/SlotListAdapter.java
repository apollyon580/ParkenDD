package de.jkliemann.parkendd;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



/**
 * TODO: document your custom view class.
 */
public class SlotListAdapter extends ArrayAdapter<ParkingSpot> {
    private final Context context;
    private final ParkingSpot[] spots;
    private final int red = Color.argb(255, 255, 0, 0);
    private final int green = Color.argb(255, 0, 155, 0);
    private final int yellow = Color.argb(255, 185, 185, 0);
    private final int blue = Color.argb(255, 0, 0, 255);
    private static final String CLOSED = "closed";
    private static final String NODATA = "nodata";
    private static final String MANY = "many";
    private static final String FEW = "few";
    private static final String FULL ="full";


    public SlotListAdapter(Context context, ParkingSpot[] spots){
        super(context, R.layout.slot_list_adapter, spots);
        this.context = context;
        this.spots = spots;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View slotView = inflater.inflate(R.layout.slot_list_adapter, parent, false);
        TextView countView = (TextView)slotView.findViewById(R.id.countView);
        TextView freeView = (TextView)slotView.findViewById(R.id.freeView);
        TextView nameView = (TextView)slotView.findViewById(R.id.nameView);
        ParkingSpot spot = spots[position];
        nameView.setText(spot.name());
        nameView.setTypeface(null, Typeface.BOLD);
        countView.setText(context.getString(R.string.view_available) + Integer.toString(spot.count()));
        if(spot.state().equals(CLOSED)) {
            freeView.setText(context.getString(R.string.closed));
            freeView.setTextColor(this.red);
        }else if(spot.state().equals(NODATA)){
            freeView.setText(context.getString(R.string.nodata));
            freeView.setTextColor(this.blue);
        }else{
            freeView.setText(context.getString(R.string.view_free) + Integer.toString(spot.free()));
            if(spot.state().equals(MANY)){
                freeView.setTextColor(this.green);
            }else if(spot.state().equals(FEW)){
                freeView.setTextColor(this.yellow);
            }else if(spot.state().equals(FULL)){
                freeView.setTextColor(this.red);
            }
        }
        return slotView;
    }
}