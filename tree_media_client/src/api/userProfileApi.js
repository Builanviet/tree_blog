const API_URL = 'http://localhost:8081/api/user_profile'; 

export const getCurrentUserProfile = async () => {
    try {
      const response = await fetch(API_URL, {
        method: 'GET',  
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, 
        },
      });
  
      if (!response.ok) {
        throw new Error('Failed to fetch trees');
      }
  
      const data = await response.json();
      return data;
    } catch (error) {
      throw error;
    }
  };

export const fetchUserProfile = async () => {
    const currentUserProfile = await getCurrentUserProfile()
    try {
      const response = await fetch(`${API_URL}/${currentUserProfile.data.id}`, {
        method: 'GET',  
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, 
        },
      });
  
      if (!response.ok) {
        throw new Error('Failed to fetch trees');
      }
  
      const data = await response.json();
      return data;
    } catch (error) {
      throw error;
    }
  };

  export const fetchUserProfileByUserId = async (userId) => {
    try {
      const response = await fetch(`${API_URL}/user/${userId}`, {
        method: 'GET',  
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, 
        },
      });
  
      if (!response.ok) {
        throw new Error('Failed to fetch trees');
      }
  
      const data = await response.json();
      return data;
    } catch (error) {
      throw error;
    }
  };