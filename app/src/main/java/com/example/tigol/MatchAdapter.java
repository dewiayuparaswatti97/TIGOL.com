package com.example.tigol;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder>  {

        // Member variables.
        private ArrayList<Match> mSportsData;
        private Context mContext;

        /**
         * Constructor that passes in the sports data and the context.
         *
         * @param sportsData ArrayList containing the sports data.
         * @param context Context of the application.
         */
        MatchAdapter(Context context, ArrayList<Match> sportsData) {
            this.mSportsData = sportsData;
            this.mContext = context;
        }


        /**
         * Required method for creating the viewholder objects.
         *
         * @param parent The ViewGroup into which the new View will be added
         *               after it is bound to an adapter position.
         * @param viewType The view type of the new View.
         * @return The newly created ViewHolder.
         */
        @Override
        public MatchAdapter.ViewHolder onCreateViewHolder(
                ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.match_list, parent, false));
        }

        /**
         * Required method that binds the data to the viewholder.
         *
         * @param holder The viewholder into which the data should be put.
         * @param position The adapter position.
         */
        @Override
        public void onBindViewHolder(MatchAdapter.ViewHolder holder,
                                     int position) {
            // Get current sport.
            Match currentSport = mSportsData.get(position);

            // Populate the textviews with data.
            holder.bindTo(currentSport);
        }


        /**
         * Required method for determining the size of the data set.
         *
         * @return Size of the data set.
         */
        @Override
        public int getItemCount() {
            return mSportsData.size();
        }


        /**
         * ViewHolder class that represents each row of data in the RecyclerView.
         */
        class ViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener{

            // Member Variables for the TextViews
            private TextView mTitleText;
            private TextView mTanggalText;
            private TextView mWaktuText;
            private TextView mHargaText;
            private TextView mHomeText;
            private TextView mAwayText;
            private ImageView mHomeImage;
            private ImageView mAwayImage;

            /**
             * Constructor for the ViewHolder, used in onCreateViewHolder().
             *
             * @param itemView The rootview of the list_item.xml layout file.
             */
            ViewHolder(View itemView) {
                super(itemView);

                // Initialize the views.
                mTitleText = itemView.findViewById(R.id.title_textView);
                mTanggalText = itemView.findViewById(R.id.tanggal_textView);
                mWaktuText = itemView.findViewById(R.id.waktu_textView);
                mHargaText = itemView.findViewById(R.id.harga_textView);
                mHomeText = itemView.findViewById(R.id.home_textView);
                mAwayText = itemView.findViewById(R.id.away_textView);

                mHomeImage = itemView.findViewById(R.id.home_image);
                mAwayImage = itemView.findViewById(R.id.away_image);

                // Set the OnClickListener to the entire view.
                itemView.setOnClickListener(this);
            }

            void bindTo(Match currentSport){
                // Populate the textviews with data.
                String[] tim = mContext.getResources().getStringArray(R.array.Teamlist);;

                Locale localeID = new Locale("in", "ID");
                final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                mTitleText.setText(currentSport.getTitle());
                mTanggalText.setText(currentSport.getTanggal());
                mWaktuText.setText(currentSport.getJam());
                mHargaText.setText(formatRupiah.format(Double.valueOf(currentSport.getHarga())));
                mHomeText.setText(tim[currentSport.getHomeTeam()]);
                mAwayText.setText(tim[currentSport.getAwayTeam()]);

                final TypedArray imgs = mContext.getResources().obtainTypedArray(R.array.DaftarGambar);
                mHomeImage.setImageResource(imgs.getResourceId(currentSport.getHomeTeam(),-1));
                mAwayImage.setImageResource(imgs.getResourceId(currentSport.getAwayTeam(),-1));


                // Load the images into the ImageView using the Glide library.
//                Glide.with(mContext).load(
//                        currentSport.getImageResource()).into(mHomeImage);
            }

            /**
             * Handle click to show DetailActivity.
             *
             * @param view View that is clicked.
             */
            @Override
            public void onClick(View view) {
//                Match currentSport = mSportsData.get(getAdapterPosition());
//                Intent detailIntent = new Intent(mContext, DetailTernak.class);
//                detailIntent.putExtra("name", currentSport.getJenis());
//                detailIntent.putExtra("jenis", currentSport.getTitle());
//                detailIntent.putExtra("umur", currentSport.getUmur());
//                detailIntent.putExtra("lokasi", currentSport.getLokasi());
//                detailIntent.putExtra("hargaBeli", currentSport.getHargaBeli());
//                detailIntent.putExtra("hargaJual", currentSport.getHargaJual());
////                detailIntent.putExtra("image_resource",
////                        currentSport.getImageResource());
//                mContext.startActivity(detailIntent);
            }
        }
    }

