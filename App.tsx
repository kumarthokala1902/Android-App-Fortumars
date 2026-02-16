
import React, { useState, useMemo } from 'react';
import { 
  FolderIcon, 
  FileIcon, 
  ChevronRightIcon, 
  ChevronDownIcon, 
  SearchIcon, 
  SmartphoneIcon, 
  CodeIcon, 
  ShoppingCartIcon, 
  ArrowLeftIcon, 
  Trash2Icon,
  CopyIcon,
  DownloadIcon,
  LinkIcon,
  UserIcon,
  MoonIcon,
  SunIcon
} from 'lucide-react';
import { ANDROID_PROJECT } from './androidProjectFiles';
import { Product, CartItem, ProjectNode, ProjectFile } from './types';

const CATEGORIES = [
  "All", "Electronics", "Men's Clothing", "Women's Clothing", 
  "Home & Kitchen", "Books", "Sports & Fitness", 
  "Beauty & Personal Care", "Toys & Games"
];

const PRODUCTS: Product[] = [
  { id: 101, name: "iPhone 15 Pro", category: "Electronics", price: 999, description: "Titanium design.", imageUrl: "https://images.unsplash.com/photo-1695048133142-1a20484d2569?q=80&w=400" },
  { id: 102, name: "Sony XM5", category: "Electronics", price: 348, description: "Noise canceling.", imageUrl: "https://images.unsplash.com/photo-1546435770-40d427791248?q=80&w=400" },
  { id: 201, name: "Oxford Shirt", category: "Men's Clothing", price: 35, description: "Classic fit.", imageUrl: "https://images.unsplash.com/photo-1596755094514-f87e34085b2c?q=80&w=400" },
  { id: 301, name: "Floral Dress", category: "Women's Clothing", price: 45, description: "Summer vibes.", imageUrl: "https://images.unsplash.com/photo-1572804013309-59a88b7e92f1?q=80&w=400" }
];

const Emulator: React.FC = () => {
  const [screen, setScreen] = useState<'home'|'detail'|'cart'>('home');
  const [selected, setSelected] = useState<Product | null>(null);
  const [cart, setCart] = useState<CartItem[]>([]);
  const [search, setSearch] = useState('');
  const [category, setCategory] = useState('All');
  const [dark, setDark] = useState(false);

  const filtered = useMemo(() => {
    return PRODUCTS.filter(p => {
      const matchCat = category === 'All' || p.category === category;
      const matchSearch = p.name.toLowerCase().includes(search.toLowerCase());
      return matchCat && matchSearch;
    });
  }, [search, category]);

  const total = cart.reduce((a, b) => a + (b.price * b.quantity), 0);

  const handleAdd = (p: Product) => {
    setCart(prev => {
      const exists = prev.find(i => i.id === p.id);
      if (exists) return prev.map(i => i.id === p.id ? { ...i, quantity: i.quantity + 1 } : i);
      return [...prev, { ...p, quantity: 1 }];
    });
  };

  return (
    <div className={`w-[320px] h-[660px] rounded-[45px] border-[8px] border-zinc-800 relative overflow-hidden shadow-2xl transition-all duration-300 ${dark ? 'bg-zinc-950 text-zinc-100' : 'bg-zinc-50 text-zinc-900'}`}>
      <div className="absolute top-0 w-full h-7 flex justify-center items-center z-20">
        <div className="w-16 h-4 bg-black rounded-b-xl"></div>
      </div>

      <div className="h-full flex flex-col pt-7">
        <header className={`px-4 py-3 flex items-center justify-between shadow-sm ${dark ? 'bg-zinc-900' : 'bg-blue-600 text-white'}`}>
          <div className="flex items-center gap-2">
            {screen !== 'home' && <button onClick={() => setScreen('home')}><ArrowLeftIcon size={16}/></button>}
            <span className="font-bold text-sm">Mart Pro</span>
          </div>
          <div className="flex items-center gap-3">
            <button onClick={() => setDark(!dark)}>{dark ? <SunIcon size={16}/> : <MoonIcon size={16}/>}</button>
            <button onClick={() => setScreen('cart')} className="relative">
              <ShoppingCartIcon size={16}/>
              {cart.length > 0 && <span className="absolute -top-2 -right-2 bg-red-500 text-white text-[8px] w-4 h-4 rounded-full flex items-center justify-center font-bold border-2 border-inherit">{cart.length}</span>}
            </button>
          </div>
        </header>

        <div className="flex-1 overflow-y-auto no-scrollbar pb-10">
          {screen === 'home' && (
            <div className="animate-in fade-in duration-300">
              <div className="p-4">
                <div className="relative">
                  <SearchIcon size={14} className="absolute left-3 top-1/2 -translate-y-1/2 text-zinc-400"/>
                  <input 
                    className={`w-full rounded-xl py-2.5 pl-9 pr-4 text-xs shadow-inner focus:ring-2 focus:ring-blue-500 outline-none transition-all ${dark ? 'bg-zinc-800 border-zinc-700' : 'bg-white border-zinc-200'}`}
                    placeholder="Search catalog..." value={search}
                    onChange={e => setSearch(e.target.value)}
                  />
                </div>
              </div>

              <div className="flex gap-2 px-4 pb-4 overflow-x-auto no-scrollbar">
                {CATEGORIES.slice(0, 4).map(cat => (
                  <button 
                    key={cat} 
                    onClick={() => setCategory(cat)}
                    className={`px-4 py-1.5 rounded-full text-[10px] font-bold border transition-all whitespace-nowrap ${category === cat ? 'bg-blue-600 border-blue-600 text-white' : dark ? 'bg-zinc-800 border-zinc-700 text-zinc-400' : 'bg-white border-zinc-200 shadow-sm'}`}
                  >
                    {cat}
                  </button>
                ))}
              </div>

              <div className="p-3 grid grid-cols-2 gap-3">
                {filtered.map(p => (
                  <div key={p.id} onClick={() => { setSelected(p); setScreen('detail'); }} className={`group relative rounded-2xl border overflow-hidden transition-all duration-300 hover:-translate-y-1 hover:shadow-xl active:scale-95 ${dark ? 'bg-zinc-900 border-zinc-800' : 'bg-white border-zinc-100 shadow-sm'}`}>
                    <div className="aspect-square overflow-hidden">
                      <img src={p.imageUrl} className="w-full h-full object-cover transition-transform duration-500 group-hover:scale-110" />
                    </div>
                    <div className="p-3">
                      <h4 className="text-[10px] font-bold truncate mb-1">{p.name}</h4>
                      <p className="text-blue-500 font-black text-xs">${p.price}</p>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )}

          {screen === 'detail' && selected && (
            <div className="animate-in slide-in-from-right duration-300">
              <img src={selected.imageUrl} className="w-full aspect-square object-cover shadow-lg"/>
              <div className="p-6 space-y-4">
                <div className="flex justify-between items-center">
                  <h2 className="font-black text-lg">{selected.name}</h2>
                  <span className="text-[10px] font-bold bg-blue-500/10 text-blue-500 px-3 py-1 rounded-full">{selected.category}</span>
                </div>
                <p className="text-xs opacity-60 leading-relaxed">{selected.description}</p>
                <div className="flex justify-between items-center pt-6">
                  <span className="text-2xl font-black text-blue-600">${selected.price}</span>
                  <button onClick={() => { handleAdd(selected); setScreen('cart'); }} className="bg-blue-600 text-white px-8 py-3 rounded-2xl text-xs font-bold shadow-lg shadow-blue-500/30 active:scale-95 transition-all">Add to Cart</button>
                </div>
              </div>
            </div>
          )}

          {screen === 'cart' && (
            <div className="p-4 space-y-4 animate-in slide-in-from-bottom duration-300">
              <h3 className="font-bold text-lg">My Cart</h3>
              {cart.length === 0 ? <div className="py-20 text-center opacity-30 text-xs italic">Your bag is empty</div> : (
                <div className="space-y-3">
                  {cart.map(i => (
                    <div key={i.id} className={`p-3 rounded-2xl border flex gap-4 items-center ${dark ? 'bg-zinc-900 border-zinc-800' : 'bg-white border-zinc-200'}`}>
                      <img src={i.imageUrl} className="w-16 h-16 rounded-xl object-cover"/>
                      <div className="flex-1 min-w-0">
                        <h5 className="text-[11px] font-bold truncate">{i.name}</h5>
                        <p className="text-blue-500 text-xs font-black">${i.price} × {i.quantity}</p>
                      </div>
                      <button onClick={() => setCart(cart.filter(x => x.id !== i.id))} className="text-red-500 p-2 hover:bg-red-500/10 rounded-lg"><Trash2Icon size={16}/></button>
                    </div>
                  ))}
                  <div className={`p-5 rounded-2xl border-2 border-dashed ${dark ? 'border-zinc-800' : 'border-zinc-200'}`}>
                    <div className="flex justify-between font-black"><span>Total Payment</span><span>${total.toFixed(2)}</span></div>
                    <button className="w-full bg-blue-600 text-white py-4 rounded-2xl mt-6 text-sm font-black shadow-xl shadow-blue-500/30">Secure Checkout</button>
                  </div>
                </div>
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

const FileNode: React.FC<{ 
  node: ProjectNode; depth: number; onSelect: (f: ProjectFile) => void; selected?: ProjectFile | null;
}> = ({ node, depth, onSelect, selected }) => {
  const [open, setOpen] = useState(depth < 3);
  if (node.type === 'folder') {
    return (
      <div className="select-none">
        <div onClick={() => setOpen(!open)} className="flex items-center gap-2 py-1.5 px-4 cursor-pointer hover:bg-white/5 transition-colors group text-sm" style={{ paddingLeft: `${depth * 16 + 16}px` }}>
          {open ? <ChevronDownIcon size={12}/> : <ChevronRightIcon size={12}/>}
          <FolderIcon size={14} className="text-blue-400 group-hover:scale-110 transition-transform"/>
          <span className="truncate opacity-80 group-hover:opacity-100">{node.name}</span>
        </div>
        {open && node.children.map((c, i) => <FileNode key={i} node={c} depth={depth+1} onSelect={onSelect} selected={selected}/>)}
      </div>
    );
  }
  return (
    <div onClick={() => onSelect(node)} className={`flex items-center gap-2 py-1.5 px-4 cursor-pointer transition-all text-sm group ${selected?.path === node.path ? 'bg-blue-500/10 text-blue-400 border-r-2 border-blue-500 shadow-inner' : 'hover:bg-white/5 opacity-60 hover:opacity-100'}`} style={{ paddingLeft: `${depth * 16 + 16}px` }}>
      <FileIcon size={14} className={node.language === 'java' ? 'text-orange-400' : node.language === 'xml' ? 'text-purple-400' : 'text-gray-400'}/>
      <span className="truncate">{node.name}</span>
    </div>
  );
};

export default function App() {
  const [file, setFile] = useState<ProjectFile | null>(() => {
    const find = (nodes: ProjectNode[]): ProjectFile | null => {
      for (const n of nodes) {
        if (n.type === 'file' && n.name === 'MainActivity.java') return n;
        if (n.type === 'folder') { const res = find(n.children); if (res) return res; }
      }
      return null;
    };
    return find(ANDROID_PROJECT);
  });

  const handleCopyCode = () => { if (file) { navigator.clipboard.writeText(file.content); alert('Copied code!'); } };
  const handleCopyPath = () => { if (file) { navigator.clipboard.writeText(file.path); alert('Copied path!'); } };
  const handleDownload = () => {
    if (file) {
      const b = new Blob([file.content], { type: 'text/plain' });
      const u = URL.createObjectURL(b);
      const a = document.createElement('a');
      a.href = u; a.download = file.name; a.click(); URL.revokeObjectURL(u);
    }
  };

  return (
    <div className="flex h-screen overflow-hidden text-gray-300 bg-[#0d1117]">
      <div className="w-72 border-r border-white/10 flex flex-col shrink-0 bg-[#161b22]">
        <div className="p-5 border-b border-white/10 flex items-center gap-3">
          <div className="w-8 h-8 bg-gradient-to-br from-blue-500 to-indigo-600 rounded-lg flex items-center justify-center font-black text-xs shadow-lg shadow-blue-500/20">M</div>
          <div>
            <span className="font-bold text-xs uppercase tracking-widest text-white block">Mart Pro</span>
            <span className="text-[9px] text-gray-500 font-bold uppercase tracking-tighter">Android Workspace</span>
          </div>
        </div>
        <div className="flex-1 overflow-y-auto no-scrollbar py-4">
          {ANDROID_PROJECT.map((n, i) => <FileNode key={i} node={n} depth={0} onSelect={setFile} selected={file}/>)}
        </div>
        <div className="p-4 bg-[#0d1117] border-t border-white/5 space-y-2">
            <p className="text-[10px] text-gray-500 font-bold uppercase text-center mb-2">Project Controls</p>
            <button onClick={() => window.location.reload()} className="w-full py-2 bg-white/5 hover:bg-white/10 text-white rounded-lg text-[10px] font-bold uppercase tracking-widest transition-all">Rebuild Build</button>
        </div>
      </div>

      <div className="flex-1 flex flex-col min-w-0">
        <div className="h-14 flex items-center bg-[#1c2128] border-b border-white/10 px-6 gap-6">
          {file ? (
            <div className="flex items-center gap-4 min-w-0">
              <span className="text-sm font-bold text-blue-400 whitespace-nowrap">{file.name}</span>
              <span className="text-[10px] text-gray-500 truncate opacity-50 font-mono hidden md:block">{file.path}</span>
            </div>
          ) : (
            <span className="text-sm opacity-50">Select a source file</span>
          )}
          
          <div className="ml-auto flex items-center gap-1 bg-[#0d1117] p-1 rounded-xl border border-white/5">
            {file && (
              <>
                <button onClick={handleCopyPath} className="flex items-center gap-1.5 px-3 py-2 hover:bg-white/5 rounded-lg text-[10px] font-bold text-gray-400 transition-all"><LinkIcon size={12}/> Path</button>
                <div className="w-px h-4 bg-white/10 mx-1"></div>
                <button onClick={handleCopyCode} className="flex items-center gap-1.5 px-3 py-2 hover:bg-white/5 rounded-lg text-[10px] font-bold text-gray-400 transition-all"><CopyIcon size={12}/> Copy</button>
                <button onClick={handleDownload} className="flex items-center gap-1.5 px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded-lg text-[10px] font-black uppercase tracking-wider transition-all shadow-lg shadow-blue-500/20"><DownloadIcon size={12}/> Download</button>
              </>
            )}
          </div>
        </div>

        <div className="flex-1 flex bg-[#0d1117] overflow-hidden">
          <div className="flex-1 overflow-auto p-10 code-font text-[13px] leading-7 no-scrollbar selection:bg-blue-500/30">
            {file ? (
              <pre className="whitespace-pre group">
                <code className="text-gray-300 block relative">
                   <div className="absolute -left-8 top-0 text-gray-600 text-[10px] text-right w-5 select-none opacity-40">
                     {file.content.split('\n').map((_, i) => <div key={i}>{i+1}</div>)}
                   </div>
                   {file.content}
                </code>
              </pre>
            ) : (
              <div className="h-full flex flex-col items-center justify-center opacity-10">
                <CodeIcon size={120} strokeWidth={0.5} />
                <p className="mt-6 font-black tracking-[0.2em] uppercase text-xl">System Ready</p>
              </div>
            )}
          </div>
          
          <div className="w-[440px] border-l border-white/10 bg-[#161b22] flex flex-col items-center justify-center shrink-0 relative overflow-hidden">
            <div className="absolute top-0 right-0 w-64 h-64 bg-blue-600/5 blur-[120px] rounded-full -translate-y-1/2 translate-x-1/2"></div>
            <div className="z-10 flex flex-col items-center">
              <div className="mb-6 flex items-center gap-3 px-4 py-2 bg-blue-500/10 rounded-full border border-blue-500/20">
                <SmartphoneIcon size={14} className="text-blue-400 animate-pulse"/>
                <span className="text-[10px] font-black uppercase tracking-widest text-blue-400">Live Device Render</span>
              </div>
              <Emulator />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
