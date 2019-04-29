package com.example.tigol;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class UserMatchAdapter extends RecyclerView.Adapter<UserMatchAdapter.ViewHolder>  {

        // Member variables.
        private ArrayList<MyMatch> mSportsData;
        private Context mContext;

        /**
         * Constructor that passes in the sports data and the context.
         *
         * @param sportsData ArrayList containing the sports data.
         * @param context Context of the application.
         */
        UserMatchAdapter(Context context, ArrayList<MyMatch> sportsData) {
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
        public UserMatchAdapter.ViewHolder onCreateViewHolder(
                ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.user_match_list, parent, false));
        }

        /**
         * Required method that binds the data to the viewholder.
         *
         * @param holder The viewholder into which the data should be put.
         * @param position The adapter position.
         */
        @Override
        public void onBindViewHolder(UserMatchAdapter.ViewHolder holder,
                                     int position) {
            // Get current sport.
            MyMatch currentSport = mSportsData.get(position);

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
            private TextView mUserText;
            private TextView mTanggalText;
            private TextView mWaktuText;
            private TextView mHargaText;
            private TextView mHomeText;
            private TextView mAwayText;
            private TextView mStadionText;
            private TextView mSeatText;
            private TextView mStatusText;
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
                mUserText = itemView.findViewById(R.id.pembeli_textView);
                mTanggalText = itemView.findViewById(R.id.tanggal_textView);
                mWaktuText = itemView.findViewById(R.id.waktu_textView);
                mSeatText = itemView.findViewById(R.id.seat_textView);
                mHargaText = itemView.findViewById(R.id.harga_textView);
                mHomeText = itemView.findViewById(R.id.home_textView);
                mAwayText = itemView.findViewById(R.id.away_textView);
                mStadionText = itemView.findViewById(R.id.stadion_textView);
                mStatusText = itemView.findViewById(R.id.status_textView);

                mHomeImage = itemView.findViewById(R.id.home_image);
                mAwayImage = itemView.findViewById(R.id.away_image);

                // Set the OnClickListener to the entire view.
                itemView.setOnClickListener(this);
            }

            void bindTo(MyMatch currentSport){
                // Populate the textviews with data.
                String[] tim = mContext.getResources().getStringArray(R.array.Teamlist);;
                String[] stadion = mContext.getResources().getStringArray(R.array.Stadium);;

                Locale localeID = new Locale("in", "ID");
                final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
                mTitleText.setText(currentSport.getTitle());
                mUserText.setText(currentSport.getNamaUser());
                mTanggalText.setText(currentSport.getTanggal());
                mWaktuText.setText(currentSport.getJam());
                mHargaText.setText(formatRupiah.format(Double.valueOf(currentSport.getHargaOutdoor())));
                mHomeText.setText(tim[currentSport.getHomeTeam()]);
                mAwayText.setText(tim[currentSport.getAwayTeam()]);
                mSeatText.setText(String.valueOf(currentSport.getSeat()) + " Seat");
                mStadionText.setText(stadion[currentSport.getStadium()]);
                mStatusText.setText(currentSport.isVerified() ? "Terbayar" : "Menunggu Konfirmasi Pembayaran");

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
                MyMatch currentSport = mSportsData.get(getAdapterPosition());
                Intent detailIntent = new Intent(mContext, UserTicketDetail.class);
                detailIntent.putExtra("key", currentSport.getKey());
                detailIntent.putExtra("namauser", currentSport.getNamaUser());
                detailIntent.putExtra("metode", currentSport.getMetode());
                detailIntent.putExtra("seat", currentSport.getSeat());
                detailIntent.putExtra("verified", mStatusText.getText().toString());
                detailIntent.putExtra("verify", currentSport.isVerified());
                detailIntent.putExtra("total", currentSport.getTotal());
                detailIntent.putExtra("title", currentSport.getTitle());
                detailIntent.putExtra("tanggal", currentSport.getTanggal());
                detailIntent.putExtra("jam", currentSport.getJam());
                detailIntent.putExtra("outdoor", currentSport.getHargaOutdoor());
                detailIntent.putExtra("reguler", currentSport.getHargaReguler());
                detailIntent.putExtra("vip", currentSport.getHargaVIP());
                detailIntent.putExtra("home", currentSport.getHomeTeam());
                detailIntent.putExtra("away", currentSport.getAwayTeam());
                detailIntent.putExtra("stadium", currentSport.getStadium());
                mContext.startActivity(detailIntent);
            }
        }
    }

