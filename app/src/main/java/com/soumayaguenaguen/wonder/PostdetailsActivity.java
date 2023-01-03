package com.soumayaguenaguen.wonder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.soumayaguenaguen.wonder.Adapters.CommentAdapter;

import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class PostdetailsActivity extends AppCompatActivity {

    ImageView imgPost,imgUserPost,imgCurrentUser;
    TextView txtPostDesc,txtPostDateName,txtPostTitle;
    EditText editTextComment;
    Button btnAddComment;
    String PostKey;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    RecyclerView RvComment;
    CommentAdapter commentAdapter;
    List<Comment> listComment;
    static String COMMENT_KEY = "Comment" ;
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetails);



            // let's set the statue bar to transparent
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            Objects.requireNonNull(getSupportActionBar()).hide();

            // ini Views
            RvComment = findViewById(R.id.rv_comment);
            imgPost = findViewById(R.id.post_detail_img);
            imgUserPost = findViewById(R.id.post_detail_img);
            imgCurrentUser = findViewById(R.id.post_detail_currentuser_img);

            txtPostTitle = findViewById(R.id.post_detail_title);
            txtPostDesc = findViewById(R.id.post_detail_desc);
            txtPostDateName = findViewById(R.id.post_detail_date_name);

            editTextComment = findViewById(R.id.post_detail_comment);
            btnAddComment = findViewById(R.id.post_detail_add_comment_btn);


            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            firebaseDatabase = FirebaseDatabase.getInstance();


            // add Comment button click listner

            btnAddComment.setOnClickListener(view -> {

                btnAddComment.setVisibility(View.INVISIBLE);
                DatabaseReference commentReference = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey).push();
                String comment_content = editTextComment.getText().toString();
                String uid = firebaseUser.getUid();
                String uname = firebaseUser.getDisplayName();
                String uimg = Objects.requireNonNull(firebaseUser.getPhotoUrl()).toString();
                class TextComment implements Comment {
                    private String text;
                    private String uid;
                    private String uimg;
                    private String uname;

                    public TextComment(String text, String uid, String uimg, String uname) {
                        this.text = text;
                        this.uid = uid;
                        this.uimg = uimg;
                        this.uname = uname;
                    }

                    public void display() {
                        System.out.println(text);
                    }

                    public Map<String, Object> toMap() {
                        HashMap<String, Object> result = new HashMap<>();
                        result.put("text", text);
                        result.put("uid", uid);
                        result.put("uimg", uimg);
                        result.put("uname", uname);
                        return result;
                    }

                    @Override
                    public String getData() throws DOMException {
                        return null;
                    }

                    @Override
                    public void setData(String data) throws DOMException {

                    }

                    @Override
                    public int getLength() {
                        return 0;
                    }

                    @Override
                    public String substringData(int offset, int count) throws DOMException {
                        return null;
                    }

                    @Override
                    public void appendData(String arg) throws DOMException {

                    }

                    @Override
                    public void insertData(int offset, String arg) throws DOMException {

                    }

                    @Override
                    public void deleteData(int offset, int count) throws DOMException {

                    }

                    @Override
                    public void replaceData(int offset, int count, String arg) throws DOMException {

                    }

                    @Override
                    public String getNodeName() {
                        return null;
                    }

                    @Override
                    public String getNodeValue() throws DOMException {
                        return null;
                    }

                    @Override
                    public void setNodeValue(String nodeValue) throws DOMException {

                    }

                    @Override
                    public short getNodeType() {
                        return 0;
                    }

                    @Override
                    public Node getParentNode() {
                        return null;
                    }

                    @Override
                    public NodeList getChildNodes() {
                        return null;
                    }

                    @Override
                    public Node getFirstChild() {
                        return null;
                    }

                    @Override
                    public Node getLastChild() {
                        return null;
                    }

                    @Override
                    public Node getPreviousSibling() {
                        return null;
                    }

                    @Override
                    public Node getNextSibling() {
                        return null;
                    }

                    @Override
                    public NamedNodeMap getAttributes() {
                        return null;
                    }

                    @Override
                    public Document getOwnerDocument() {
                        return null;
                    }

                    @Override
                    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
                        return null;
                    }

                    @Override
                    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
                        return null;
                    }

                    @Override
                    public Node removeChild(Node oldChild) throws DOMException {
                        return null;
                    }

                    @Override
                    public Node appendChild(Node newChild) throws DOMException {
                        return null;
                    }

                    @Override
                    public boolean hasChildNodes() {
                        return false;
                    }

                    @Override
                    public Node cloneNode(boolean deep) {
                        return null;
                    }

                    @Override
                    public void normalize() {

                    }

                    @Override
                    public boolean isSupported(String feature, String version) {
                        return false;
                    }

                    @Override
                    public String getNamespaceURI() {
                        return null;
                    }

                    @Override
                    public String getPrefix() {
                        return null;
                    }

                    @Override
                    public void setPrefix(String prefix) throws DOMException {

                    }

                    @Override
                    public String getLocalName() {
                        return null;
                    }

                    @Override
                    public boolean hasAttributes() {
                        return false;
                    }

                    @Override
                    public String getBaseURI() {
                        return null;
                    }

                    @Override
                    public short compareDocumentPosition(Node other) throws DOMException {
                        return 0;
                    }

                    @Override
                    public String getTextContent() throws DOMException {
                        return null;
                    }

                    @Override
                    public void setTextContent(String textContent) throws DOMException {

                    }

                    @Override
                    public boolean isSameNode(Node other) {
                        return false;
                    }

                    @Override
                    public String lookupPrefix(String namespaceURI) {
                        return null;
                    }

                    @Override
                    public boolean isDefaultNamespace(String namespaceURI) {
                        return false;
                    }

                    @Override
                    public String lookupNamespaceURI(String prefix) {
                        return null;
                    }

                    @Override
                    public boolean isEqualNode(Node arg) {
                        return false;
                    }

                    @Override
                    public Object getFeature(String feature, String version) {
                        return null;
                    }

                    @Override
                    public Object setUserData(String key, Object data, UserDataHandler handler) {
                        return null;
                    }

                    @Override
                    public Object getUserData(String key) {
                        return null;
                    }
                }

// ...

                TextComment comment = new TextComment(comment_content, uid, uimg, uname);
                Map<String, Object> commentMap = comment.toMap();
                commentReference.setValue(commentMap);
                commentReference.setValue(comment).addOnSuccessListener(aVoid -> {
                    showMessage("comment added");
                    editTextComment.setText("");
                    btnAddComment.setVisibility(View.VISIBLE);
                }).addOnFailureListener(e -> showMessage("fail to add comment : "+e.getMessage()));



            });


            // now we need to bind all data into those views
            // firt we need to get post data
            // we need to send post detail data to this activity first ...
            // now we can get post data

            String postImage = getIntent().getExtras().getString("postImage") ;
            Glide.with(this).load(postImage).into(imgPost);

            String postTitle = getIntent().getExtras().getString("title");
            txtPostTitle.setText(postTitle);

            String userpostImage = getIntent().getExtras().getString("userPhoto");
            Glide.with(this).load(userpostImage).into(imgUserPost);

            String postDescription = getIntent().getExtras().getString("description");
            txtPostDesc.setText(postDescription);

            // setcomment user image

            Glide.with(this).load(firebaseUser.getPhotoUrl()).into(imgCurrentUser);
            // get post id
            PostKey = getIntent().getExtras().getString("postKey");

            String date = timestampToString(getIntent().getExtras().getLong("postDate"));
            txtPostDateName.setText(date);


            // ini Recyclerview Comment
            iniRvComment();


        }

        private void iniRvComment() {

            RvComment.setLayoutManager(new LinearLayoutManager(this));

            DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY).child(PostKey);
            commentRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listComment = new ArrayList<>();
                    for (DataSnapshot snap:dataSnapshot.getChildren()) {

                        Comment comment = snap.getValue(Comment.class);
                        listComment.add(comment) ;

                    }

                    commentAdapter = new CommentAdapter(getApplicationContext(),
                            listComment);
                    RvComment.setAdapter(commentAdapter);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        }

        private void showMessage(String message) {

            Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        }


        private String timestampToString(long time) {

            Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
            calendar.setTimeInMillis(time);
            return DateFormat.format("dd-MM-yyyy",calendar).toString();


        }


    }
