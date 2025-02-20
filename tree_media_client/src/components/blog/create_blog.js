import Modal from "react-modal";
import { useState } from "react";
import { FormControl, InputLabel, MenuItem, Select } from '@mui/material';
import { createBlog } from "../../api/blogApi";
import EmojiPicker from "emoji-picker-react";
import { MdOutlineEmojiEmotions } from "react-icons/md";

Modal.setAppElement("#root");

export default function CreateBlog({userProfile,onNewBlogCreated }){

    const options = [
        { value: 'private', label: 'Private' },
        { value: 'friend', label: 'Friend' },
        { value: 'public', label: 'Public' },
      ];
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [value, setValue] = useState('');
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('')
    const [showEmojiPicker, setShowEmojiPicker] = useState(false);
    const [emoji, setEmoji] = useState('');

    const handleEmojiClick = (emojiObject) => {
        setContent(content + emojiObject.emoji); 
        setShowEmojiPicker(false); 
      };

    const handleChange = (event) => {
        setValue(event.target.value);
    };

    async function handleSubmit(e){
        e.preventDefault();
        const blogData = {
            title,
            content,
            visibility: value,
        }
        try {
            const newBlog = await createBlog(blogData);
            console.log(newBlog);
    
            setModalIsOpen(false);
    
            if(onNewBlogCreated){
                onNewBlogCreated()
            }
        } catch (error) {
            console.error("Error creating blog:", error);
        }
    }
    

    return <div style={{
        border: "1px solid #ddd",
        backgroundColor:"#FFFFFF", 
        maxWidth:"60%",
        margin: "10px", 
        padding: "10px",
        borderRadius: "20px"
    }}>
            <img 
                src={userProfile.avatar}
                alt="avatar"
                style={{ width: "50px", height: "50px", borderRadius: "50%",marginTop:"20px"}}
            />
            <button onClick={() => setModalIsOpen(true)} style={{borderRadius:"20px",marginLeft:"20px",width:"80%",height:"50px"}}>What do you think</button>
            <Modal
                isOpen={modalIsOpen}
                onRequestClose={() => setModalIsOpen(false)}
                style={{
                overlay: { backgroundColor: "rgba(0, 0, 0, 0.5)" },
                content: {
                    padding: "20px",
                    maxWidth: "600px",
                    maxHeight: "800px",
                    margin: "100px auto",
                    borderRadius: "10px",
                    },
                }}
                >
                <form onSubmit={handleSubmit}>
                <div style={{}}>
                    <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between" }}>
                        <h2>Create a Post</h2>
                        <button onClick={() => setModalIsOpen(false)}>Close</button>
                    </div>
                    <div style={{backgroundColor:"gray",width:"100%",height:"1px"}}></div>
                    <div style={{ display: "flex", alignItems: "center" }}>
                        <img 
                            src={userProfile.avatar}
                            alt="avatar"
                            style={{ width: "60px", height: "60px", borderRadius: "50%",marginTop:"20px"}}
                        />
                        <div style={{marginLeft:"20px"}}>
                            <h3>Quang Thuan</h3>
                            <FormControl style={{width:"100px",margin:""}}>
                            <InputLabel id="demo-simple-select-label">Visibility</InputLabel>
                                <Select
                                    labelId="demo-simple-select-label"
                                    id="demo-simple-select"
                                    value={value}
                                    label="Visibility"
                                    onChange={handleChange}
                                    sx={{
                                        height: "40px", 
                                        fontSize: "10px", 
                                    }}
                                >
                                    <MenuItem value="PRIVATE">Private</MenuItem>
                                    <MenuItem value="FRIEND">Friend</MenuItem>
                                    <MenuItem value="PUBLIC">Public</MenuItem>
                                </Select>
                            </FormControl>
                        </div>
                        
                    </div>
                    <input 
                        placeholder="Title" 
                        style={{width:"100%",height:"50px",margin:"10px 0",border: "1px solid #ddd",borderRadius:"10px"}}
                        value={title}
                        onChange={(e) => setTitle(e.target.value)} >
                    </input>
                    <textarea 
                        placeholder="Enter content here" 
                        style={{width:"100%",height:"150px",border: "1px solid #ddd",borderRadius:"10px"}}
                        value={content}
                        onChange={(e) => setContent(e.target.value)}>
                    </textarea>
                    <div style={{border: "1px solid #ddd",borderRadius:"10px",width:"100%",height:"100%",margin:"10px 0"}}>
                        <h5 style={{marginLeft:"10px"}}>Add more to your post</h5>
                        <button
                            type="button"
                            onClick={() => setShowEmojiPicker(!showEmojiPicker)}
                            style={{
                            backgroundColor: '#FFFFFF',
                            padding: '10px 20px',
                            borderRadius: '5px',
                            cursor: 'pointer',
                            }}
                        >
                            {showEmojiPicker ? <MdOutlineEmojiEmotions /> : <MdOutlineEmojiEmotions style={{color:"yellow",height:"20px",width:"20px"}}/>}
                        </button>
                        {showEmojiPicker && (
                            <EmojiPicker
                            onEmojiClick={handleEmojiClick}
                            pickerStyle={{ position: 'absolute', bottom: '20px' }}
                            />
                        )}
                    </div>
                    <button type="submit" style={{width:"100%",height:"50px",margin:"10px 0",backgroundColor:"green",borderRadius:"10px"}}>Post</button>
                </div>
                </form>
                
            </Modal>
            <div style={{height:"1px",backgroundColor:"gray",width:"90%",margin:"15px"}}></div>
            <button style={{backgroundColor: "#C0C0C0",margin:"10px"}}><h4 style={{margin:"10px"}}>Photo</h4></button>
            <button style={{backgroundColor: "#C0C0C0",margin:"10px"}}><h4 style={{margin:"10px"}}>Videos</h4></button>
    </div>
}