package mk.advtl.justorilib.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

import mk.advtl.justorilib.R;
import mk.advtl.justorilib.models.StoryImage;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private ArrayList<StoryImage> mSliderItems;

    public SliderAdapter(Context context, ArrayList<StoryImage> mSliderItems) {
        this.context = context;
        this.mSliderItems = mSliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {


        try {
            String finalImagePath = mSliderItems.get(position).getImageName();
            //Log.e("finalSliderPath", "finalSliderPath >> " + finalImagePath);

            Glide.with(viewHolder.itemView)
                    .load(finalImagePath).apply(
                    RequestOptions.noTransformation()
                            .dontAnimate()
                            //.placeholder(R.drawable.landscape_place_holder)
            )
                    .into(viewHolder.imageViewBackground);
        } catch (Exception e) {
            e.printStackTrace();
        }

/*        viewHolder.rlMainLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });*/
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        ImageView imageViewBackground;
        RelativeLayout rlMainLay;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.image);
            rlMainLay = itemView.findViewById(R.id.rlMainLay);


        }
    }
}
