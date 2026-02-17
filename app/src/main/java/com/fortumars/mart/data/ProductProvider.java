package com.fortumars.mart.data;

import com.fortumars.mart.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductProvider {
    private static List<Product> products = null;

    public static List<Product> getAllProducts() {
        if (products == null) {
            products = new ArrayList<>();
            // 1. Electronics
            addElectronics(products);
            // 2. Men's Clothing
            addMensClothing(products);
            // 3. Women's Clothing
            addWomensClothing(products);
            // 4. Home & Kitchen
            addHomeKitchen(products);
            // 5. Books
            addBooks(products);
            // 6. Sports & Fitness
            addSportsFitness(products);
            // 7. Beauty & Personal Care
            addBeauty(products);
            // 8. Toys & Games
            addToys(products);
        }
        return products;
    }

    public static void addProduct(Product product) {
        if (products == null) {
            getAllProducts();
        }
        products.add(0, product); // Add to the top
    }

    private static void addElectronics(List<Product> products) {
        products.add(new Product("101", "iPhone 15 Pro", "Electronics", 999.0, "Titanium design, A17 Pro chip.", "https://images.unsplash.com/photo-1695048133142-1a20484d2569?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("102", "Samsung 4K TV", "Electronics", 549.99, "Crystal UHD Smart TV with HDR.", "https://images.unsplash.com/photo-1593359677879-a4bb92f829d1?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("103", "Sony WH-1000XM5", "Electronics", 348.0, "Industry leading noise canceling.", "https://images.unsplash.com/photo-1670054700055-662580556f2e?auto=format&fit=crop&q=80&w=400", 4.9f));
        products.add(new Product("104", "Gaming Laptop", "Electronics", 1299.0, "RTX 4060, 16GB RAM, 512GB SSD.", "https://images.unsplash.com/photo-1603302576837-37561b2e2302?auto=format&fit=crop&q=80&w=400", 4.7f));
        products.add(new Product("105", "Apple Watch S9", "Electronics", 399.0, "Advanced health tracking.", "https://images.unsplash.com/photo-1546868871-70ca1bc73db9?auto=format&fit=crop&q=80&w=400", 4.6f));
        products.add(new Product("106", "Power Bank 20K", "Electronics", 45.0, "High-speed charging for mobile.", "https://images.unsplash.com/photo-1609091839311-d5365f9ff1c5?auto=format&fit=crop&q=80&w=400", 4.4f));
        products.add(new Product("107", "Mirrorless Camera", "Electronics", 799.0, "Professional mirrorless camera.", "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?auto=format&fit=crop&q=80&w=400", 4.9f));
        products.add(new Product("108", "Android Tablet", "Electronics", 299.0, "10-inch display for media.", "https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("109", "Sony PlayStation 5", "Electronics", 499.0, "Next-gen gaming console.", "https://images.unsplash.com/photo-1606144042614-b2417e99c4e3?auto=format&fit=crop&q=80&w=400", 4.9f));
        products.add(new Product("110", "Kindle Paperwhite", "Electronics", 139.0, "Waterproof e-reader with 6.8\" display.", "https://images.unsplash.com/photo-1594918808191-2357303c683b?auto=format&fit=crop&q=80&w=400", 4.8f));
    }

    private static void addMensClothing(List<Product> products) {
        products.add(new Product("201", "Oxford Shirt", "Men's Clothing", 35.0, "Classic cotton button-down.", "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?auto=format&fit=crop&q=80&w=400", 4.3f));
        products.add(new Product("202", "Slim Fit Jeans", "Men's Clothing", 69.90, "Timeless denim for all-day comfort.", "https://images.unsplash.com/photo-1542272604-787c3835535d?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("203", "Leather Moto Jacket", "Men's Clothing", 150.0, "Rugged faux leather style.", "https://images.unsplash.com/photo-1551028719-00167b16eac5?auto=format&fit=crop&q=80&w=400", 4.7f));
        products.add(new Product("204", "White Sneakers", "Men's Clothing", 75.0, "Minimalist design everyday shoes.", "https://images.unsplash.com/photo-1525966222134-fcfa99b8ae77?auto=format&fit=crop&q=80&w=400", 4.2f));
        products.add(new Product("205", "Winter Hoodie", "Men's Clothing", 35.0, "Soft fleece-lined pullover.", "https://images.unsplash.com/photo-1556821840-3a63f95609a7?auto=format&fit=crop&q=80&w=400", 4.4f));
        products.add(new Product("206", "Formal Suit Blazer", "Men's Clothing", 120.0, "Polished look for office.", "https://images.unsplash.com/photo-1594938298603-c8148c4dae35?auto=format&fit=crop&q=80&w=400", 4.6f));
        products.add(new Product("207", "Cargo Shorts", "Men's Clothing", 25.0, "Summer wear with utility pockets.", "https://images.unsplash.com/photo-1591195853828-11db59a44f6b?auto=format&fit=crop&q=80&w=400", 4.1f));
        products.add(new Product("208", "Steel Chronograph", "Men's Clothing", 199.0, "Water resistant luxury watch.", "https://images.unsplash.com/photo-1524592094714-0f0654e20314?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("209", "Leather Belt", "Men's Clothing", 25.0, "Genuine leather classic belt.", "https://images.unsplash.com/photo-1624222247344-550fb805831f?auto=format&fit=crop&q=80&w=400", 4.4f));
        products.add(new Product("210", "Cotton Socks 5-pack", "Men's Clothing", 15.0, "Soft breathable daily socks.", "https://images.unsplash.com/photo-1582967788606-a171c1080cb0?auto=format&fit=crop&q=80&w=400", 4.6f));
    }

    private static void addWomensClothing(List<Product> products) {
        products.add(new Product("301", "Floral Summer Dress", "Women's Clothing", 45.0, "Light and breezy summer wear.", "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1?auto=format&fit=crop&q=80&w=400", 4.6f));
        products.add(new Product("302", "High-Waist Blue Jeans", "Women's Clothing", 55.0, "Flattering fit with modern cut.", "https://images.unsplash.com/photo-1541099649105-f69ad21f3246?auto=format&fit=crop&q=80&w=400", 4.4f));
        products.add(new Product("303", "Wool Trench Coat", "Women's Clothing", 130.0, "Elegant winter protection.", "https://images.unsplash.com/photo-1539533018447-63fcce2678e3?auto=format&fit=crop&q=80&w=400", 4.7f));
        products.add(new Product("304", "Silk Blouse", "Women's Clothing", 40.0, "Premium silk for office.", "https://images.unsplash.com/photo-1589483232748-515c025575ba?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("305", "Yoga Leggings", "Women's Clothing", 30.0, "High compression fitness wear.", "https://images.unsplash.com/photo-1506629082923-79ad627966ee?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("306", "Knit Sweater", "Women's Clothing", 35.0, "Cozy autumn layers.", "https://images.unsplash.com/photo-1576185055363-6d7c88000919?auto=format&fit=crop&q=80&w=400", 4.3f));
        products.add(new Product("307", "Leather Tote", "Women's Clothing", 75.0, "Spacious daily essential bag.", "https://images.unsplash.com/photo-1584917865442-de89df76afd3?auto=format&fit=crop&q=80&w=400", 4.7f));
        products.add(new Product("308", "Denim Mini Skirt", "Women's Clothing", 28.0, "Versatile casual bottom.", "https://images.unsplash.com/photo-1582142306909-195724d33ffc?auto=format&fit=crop&q=80&w=400", 4.2f));
        products.add(new Product("309", "Silk Scarf", "Women's Clothing", 20.0, "Elegant printed silk accessory.", "https://images.unsplash.com/photo-1601924994987-69e26d50dc26?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("310", "Gold Hoop Earrings", "Women's Clothing", 45.0, "14k gold plated lightweight hoops.", "https://images.unsplash.com/photo-1535633302723-999aa6a75452?auto=format&fit=crop&q=80&w=400", 4.7f));
    }

    private static void addHomeKitchen(List<Product> products) {
        products.add(new Product("401", "Non-Stick Pan", "Home & Kitchen", 59.0, "Professional grade cookware.", "https://images.unsplash.com/photo-1584990344321-27061766944a?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("402", "Espresso Machine", "Home & Kitchen", 199.0, "Premium barista coffee.", "https://images.unsplash.com/photo-1510526336303-97cc9c35414d?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("403", "HEPA Air Purifier", "Home & Kitchen", 120.0, "Clean air for large rooms.", "https://images.unsplash.com/photo-1585771724684-25271286bb29?auto=format&fit=crop&q=80&w=400", 4.6f));
        products.add(new Product("404", "Luxury Bed Sheets", "Home & Kitchen", 45.0, "Egyptian cotton 800TC.", "https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af?auto=format&fit=crop&q=80&w=400", 4.4f));
        products.add(new Product("405", "Stand Mixer", "Home & Kitchen", 299.0, "Essential for home baking.", "https://images.unsplash.com/photo-1594385208974-2e75f9d8ad48?auto=format&fit=crop&q=80&w=400", 4.9f));
        products.add(new Product("406", "Robot Vacuum", "Home & Kitchen", 249.0, "Smart mapping floor cleaner.", "https://images.unsplash.com/photo-1518640467707-6811f4a6ab73?auto=format&fit=crop&q=80&w=400", 4.7f));
        products.add(new Product("407", "Chef Knife Set", "Home & Kitchen", 89.0, "Stainless steel forged blades.", "https://images.unsplash.com/photo-1593618998160-e34014e67546?auto=format&fit=crop&q=80&w=400", 4.3f));
        products.add(new Product("408", "Kitchen Blender", "Home & Kitchen", 79.0, "Smoothies and shakes pro.", "https://images.unsplash.com/photo-1570222094114-d054a817e56b?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("409", "Weighted Blanket", "Home & Kitchen", 85.0, "Deep touch pressure for better sleep.", "https://images.unsplash.com/photo-1584100936595-c0654b55a2e6?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("410", "Scented Candle Set", "Home & Kitchen", 30.0, "Natural soy wax aromatherapy.", "https://images.unsplash.com/photo-1603006905003-be475563bc59?auto=format&fit=crop&q=80&w=400", 4.6f));
    }

    private static void addBooks(List<Product> products) {
        products.add(new Product("501", "The Great Gatsby", "Books", 12.5, "Classic American fiction.", "https://images.unsplash.com/photo-1543005128-d1433b5c879d?auto=format&fit=crop&q=80&w=400", 4.7f));
        products.add(new Product("502", "Sapiens", "Books", 18.0, "History of humankind.", "https://images.unsplash.com/photo-1544947950-fa07a98d237f?auto=format&fit=crop&q=80&w=400", 4.9f));
        products.add(new Product("503", "Atomic Habits", "Books", 15.0, "Behavioral science guide.", "https://images.unsplash.com/photo-1589829085413-56de8ae18c73?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("504", "Mastering Cooking", "Books", 25.0, "Gourmet recipes guide.", "https://images.unsplash.com/photo-1556910103-1c02745aae4d?auto=format&fit=crop&q=80&w=400", 4.4f));
        products.add(new Product("505", "The Alchemist", "Books", 10.99, "Spiritual journey fable.", "https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&q=80&w=400", 4.6f));
        products.add(new Product("506", "1984 George Orwell", "Books", 14.0, "Classic dystopian novel.", "https://images.unsplash.com/photo-1531988041414-d058c3f585d1?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("507", "History of Art", "Books", 35.0, "Global artistic movements.", "https://images.unsplash.com/photo-1516979187457-637abb4f9353?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("508", "Children's Stories", "Books", 9.99, "Bedtime magic collection.", "https://images.unsplash.com/photo-1491843325421-1d7010a30030?auto=format&fit=crop&q=80&w=400", 4.3f));
        products.add(new Product("509", "7 Habits", "Books", 22.0, "Personal change classic.", "https://images.unsplash.com/photo-1544947950-fa07a98d237f?auto=format&fit=crop&q=80&w=400", 4.9f));
        products.add(new Product("510", "Thinking, Fast and Slow", "Books", 20.0, "Exploration of the mind.", "https://images.unsplash.com/photo-1589829085413-56de8ae18c73?auto=format&fit=crop&q=80&w=400", 4.7f));
    }

    private static void addSportsFitness(List<Product> products) {
        products.add(new Product("601", "Yoga Mat", "Sports & Fitness", 25.0, "Extra thick non-slip mat.", "https://images.unsplash.com/photo-1592419044706-39796d40f98c?auto=format&fit=crop&q=80&w=400", 4.7f));
        products.add(new Product("602", "Dumbbells", "Sports & Fitness", 149.0, "Adjustable home weight set.", "https://images.unsplash.com/photo-1583454110551-21f2fa2adfcd?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("603", "Tennis Racket", "Sports & Fitness", 85.0, "Carbon fiber pro frame.", "https://images.unsplash.com/photo-1595435934249-5df7ed86e1c0?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("604", "Bike Helmet", "Sports & Fitness", 40.0, "Impact resistant safety.", "https://images.unsplash.com/photo-1558223610-b99863484f9b?auto=format&fit=crop&q=80&w=400", 4.6f));
        products.add(new Product("605", "Running Trainers", "Sports & Fitness", 110.0, "High performance cushion.", "https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&q=80&w=400", 4.9f));
        products.add(new Product("606", "Resistance Bands", "Sports & Fitness", 15.0, "Portable gym equipment.", "https://images.unsplash.com/photo-1517130038641-a774d04afb3c?auto=format&fit=crop&q=80&w=400", 4.3f));
        products.add(new Product("607", "Sports Bottle", "Sports & Fitness", 12.0, "Insulated steel flask.", "https://images.unsplash.com/photo-1602143399827-705204c45224?auto=format&fit=crop&q=80&w=400", 4.4f));
        products.add(new Product("608", "Pro Basketball", "Sports & Fitness", 29.99, "Indoor/Outdoor grip ball.", "https://images.unsplash.com/photo-1546519638-68e109498ffc?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("609", "Foam Roller", "Sports & Fitness", 20.0, "Muscle recovery and massage.", "https://images.unsplash.com/photo-1600881333168-2ed49ca23a51?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("610", "Pull Up Bar", "Sports & Fitness", 35.0, "Doorway gym for upper body.", "https://images.unsplash.com/photo-1598289431512-b97b0917a63e?auto=format&fit=crop&q=80&w=400", 4.6f));
    }

    private static void addBeauty(List<Product> products) {
        products.add(new Product("701", "Vitamin C Serum", "Beauty & Personal Care", 18.0, "Anti-aging skin repair.", "https://images.unsplash.com/photo-1620916566398-39f1143ab7be?auto=format&fit=crop&q=80&w=400", 4.7f));
        products.add(new Product("702", "Liquid Lipstick", "Beauty & Personal Care", 14.5, "24h matte finish color.", "https://images.unsplash.com/photo-1586771107445-d3ca888129ee?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("703", "Ionic Hair Dryer", "Beauty & Personal Care", 45.0, "Quick dry salons finish.", "https://images.unsplash.com/photo-1522338242992-e1a54906a8da?auto=format&fit=crop&q=80&w=400", 4.6f));
        products.add(new Product("704", "Electric Toothbrush", "Beauty & Personal Care", 65.0, "Sonic pulse technology.", "https://images.unsplash.com/photo-1559591937-e647af8df55a?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("705", "Moisturizer", "Beauty & Personal Care", 22.0, "Lightweight daily hydration.", "https://images.unsplash.com/photo-1556228720-195a672e8a03?auto=format&fit=crop&q=80&w=400", 4.4f));
        products.add(new Product("706", "Shimmer Palette", "Beauty & Personal Care", 35.0, "Professional eyeshadow set.", "https://images.unsplash.com/photo-1512496015851-a90fb38ba796?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("707", "Beard Kit Pro", "Beauty & Personal Care", 28.0, "Grooming and care set.", "https://images.unsplash.com/photo-1503951914875-452162b0f3f1?auto=format&fit=crop&q=80&w=400", 4.3f));
        products.add(new Product("708", "Luxury Perfume", "Beauty & Personal Care", 75.0, "Classic floral essence.", "https://images.unsplash.com/photo-1541643600914-78b084683601?auto=format&fit=crop&q=80&w=400", 4.9f));
        products.add(new Product("709", "Sunscreen SPF 50", "Beauty & Personal Care", 15.0, "Broad spectrum protection.", "https://images.unsplash.com/photo-1556229167-da39d469a473?auto=format&fit=crop&q=80&w=400", 4.8f));
        products.add(new Product("710", "Face Mask Sheet Set", "Beauty & Personal Care", 20.0, "Hydrating Korean skincare.", "https://images.unsplash.com/photo-1596755389378-7d0d244993e5?auto=format&fit=crop&q=80&w=400", 4.7f));
    }

    private static void addToys(List<Product> products) {
        products.add(new Product("801", "LEGO Space Set", "Toys & Games", 49.99, "Interactive building fun.", "https://images.unsplash.com/photo-1585366119957-e556f4bc8070?auto=format&fit=crop&q=80&w=400", 4.9f));
        products.add(new Product("802", "Remote Control Car", "Toys & Games", 35.0, "High-speed drift racer.", "https://images.unsplash.com/photo-1532330393533-443990a51d10?auto=format&fit=crop&q=80&w=400", 4.4f));
        products.add(new Product("803", "Plush Teddy", "Toys & Games", 19.0, "Ultra soft companion.", "https://images.unsplash.com/photo-1559440666-4477c2825165?auto=format&fit=crop&q=80&w=400", 4.6f));
        products.add(new Product("804", "Strategy Board Game", "Toys & Games", 25.0, "Fun for the whole family.", "https://images.unsplash.com/photo-1610890732551-21306d368140?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("805", "Action Hero Figure", "Toys & Games", 12.0, "Fully posable warrior.", "https://images.unsplash.com/photo-1566576721346-d4a3b4eaad5b?auto=format&fit=crop&q=80&w=400", 4.2f));
        products.add(new Product("806", "Drawing Tablet", "Toys & Games", 15.0, "Electronic sketch pad.", "https://images.unsplash.com/photo-1512428559083-a401c438499b?auto=format&fit=crop&q=80&w=400", 4.1f));
        products.add(new Product("807", "Brick Box 100pc", "Toys & Games", 22.0, "Creative building blocks.", "https://images.unsplash.com/photo-1587654780291-39c9404d746b?auto=format&fit=crop&q=80&w=400", 4.7f));
        products.add(new Product("808", "1000pc Puzzle", "Toys & Games", 14.99, "Scenic landscape puzzle.", "https://images.unsplash.com/photo-1596466607760-45f85e227049?auto=format&fit=crop&q=80&w=400", 4.4f));
        products.add(new Product("809", "Rubik's Cube", "Toys & Games", 10.0, "Classic 3x3 brain teaser.", "https://images.unsplash.com/photo-1591991731833-b4807cf7ef94?auto=format&fit=crop&q=80&w=400", 4.5f));
        products.add(new Product("810", "Magic Set", "Toys & Games", 25.0, "Beginner tricks for kids.", "https://images.unsplash.com/photo-1517260911058-0fcfd733702f?auto=format&fit=crop&q=80&w=400", 4.3f));
    }
}
