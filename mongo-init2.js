// Use the target database
db = db.getSiblingDB('store');

// Create a collection
db.createCollection('subjects2');

// Insert initial data if needed
db.mycollection.insert({
                           _id: ObjectId('65281b6ba4e632eaeb13c08a'),
                           username: 'marek',
                           password: '$2a$10$Kfcn0c/0dn1AZBY0zOJmEu/t1EGXvEAO1N523CF4G94a.QL/elv52',
                           role: 'CUSTOMER'
                       });