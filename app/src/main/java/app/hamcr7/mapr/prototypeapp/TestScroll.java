package app.hamcr7.mapr.prototypeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class TestScroll extends AppCompatActivity {

    private ArrayList<String> cadCatList = new ArrayList<>();
    private String cadCatString = "";
//
//    @BindView(R.id.rv_interest_multi)
//    ChipRecyclerView rvInterestMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scroll);

//        ChipView cvTag = findViewById(R.id.cvTag);
//        ArrayList<Object> data = new ArrayList<>();

        ButterKnife.bind(this);
        setInterestAdapterMulti();
    }

    //For Cad Category Chip View Selection
    public void setInterestAdapterMulti() {
        List<UserListData> userListData = new ArrayList<>();
        String[] interestArray = getResources().getStringArray(R.array.Customization_Types);
        for (int i = 0; i < interestArray.length; i++) {
            UserListData guestUserListData = new UserListData();
            guestUserListData.setName(interestArray[i]);
            guestUserListData.setSelected(false);
            userListData.add(guestUserListData);
        }
//        CadCategoryAdapter interestAdapterMulti = new CadCategoryAdapter(this,
//                userListData, rvInterestMulti.isMultiChoiceMode());
//        rvInterestMulti.setAdapter(interestAdapterMulti);


    }
    public void selectGuestUserListData(List<UserListData> modifiedListUserData) {
        cadCatList = new ArrayList<>();
        for (int i = 0; i < modifiedListUserData.size(); i++) {
            if (modifiedListUserData.get(i).isSelected()) {
                cadCatList.add(modifiedListUserData.get(i).getName());
            }

        }
        cadCatString = cadCatList.toString().replaceAll("[\\[.\\].\\s+]", "");
    }

}
