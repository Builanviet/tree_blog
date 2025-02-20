import { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { fetchUserProfile } from "../../api/userProfileApi";
import { fetchUserBlog } from "../../api/blogApi";
import { FaHome,FaUserFriends   } from 'react-icons/fa';
import { BsCalendar2EventFill } from "react-icons/bs";
import { MdOutlineSettingsSuggest,MdGroups2  } from "react-icons/md";

export default function Sidebar(){

    const [username, setUsername] = useState("");
    const [bio, setBio] = useState("");
    const [avatar, setAvatar] = useState("");
    const [numberOfPosts, setNumberOfPosts] = useState(0);

    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("accessToken");
        if (!token) {
          navigate("/login");
          return;
        }
    
        const fetchUser = async () => {
            try {
                const userProfile = await fetchUserProfile(); 
                setUsername(userProfile.data.user.username);
                setBio(userProfile.data.bio);
                setAvatar(userProfile.data.avatar)
              } catch (err) {
                
              }
        };
    
        fetchUser();
      }, []);

      useEffect(() => {
        const token = localStorage.getItem("accessToken");
        if (!token) {
          navigate("/login");
          return;
        }
    
        const fetchBlog = async () => {
            try {
                const data = await fetchUserBlog(); 
                const count = Array.isArray(data.data) ? data.data.length : (data?.data?.length || 0); 
                setNumberOfPosts(count)
              } catch (err) {
                
              }
        };
    
        fetchBlog();
      }, []);
    

    return <div style={{
        backgroundColor: "#FFFFFF",
        width:"300px",
        maxHeight:"700px",
        margin:"100px",
        marginRight:"30px",
        borderRadius:"10px",
        display: "flex", 
        flexDirection: "column", 
        alignItems: "center", 

    }}>
        <img
            src={avatar}
            alt="Logo"
            style={{ marginTop: "30px", height: "80px", verticalAlign: "middle" }}
          />
        <h1>{username}</h1>
        <p style={{fontSize:"20px"}}>{bio}</p>
        <h3>{numberOfPosts} posts</h3>
        <div style={{backgroundColor:"gray",height:"2px",width:"100%"}}></div>
        <ul style={{ listStyleType: 'none', padding: 0 }}>
          <li style={{ display: 'flex', alignItems: 'center' }}>
            <FaHome style={{ fontSize: '20px', color: 'green', marginRight: '10px' }} />
            <h3>Homepage</h3>
          </li>
          <li style={{ display: 'flex', alignItems: 'center' }}>
            <FaUserFriends style={{ fontSize: '20px', color: 'green', marginRight: '10px' }} />
            <h3>Connections</h3>
          </li>
          <li style={{ display: 'flex', alignItems: 'center' }}>
            <BsCalendar2EventFill style={{ fontSize: '20px', color: 'green', marginRight: '10px' }} />
            <h3>Events</h3>
          </li>
          <li style={{ display: 'flex', alignItems: 'center' }}>
            <MdGroups2  style={{ fontSize: '20px', color: 'green', marginRight: '10px' }} />
            <h3>Groups</h3>
          </li>
          <li style={{ display: 'flex', alignItems: 'center' }}>
            <MdOutlineSettingsSuggest style={{ fontSize: '20px', color: 'green', marginRight: '10px' }} />
            <h3>Settings</h3>
          </li>
        </ul>
    </div>


}