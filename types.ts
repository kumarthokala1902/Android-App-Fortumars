
export interface Product {
  id: number;
  name: string;
  price: number;
  description: string;
  imageUrl: string;
  category: string;
}

export interface CartItem extends Product {
  quantity: number;
}

export interface ProjectFile {
  path: string;
  name: string;
  content: string;
  language: 'java' | 'xml' | 'gradle' | 'json';
  type: 'file';
}

export interface ProjectFolder {
  name: string;
  children: (ProjectFile | ProjectFolder)[];
  type: 'folder';
}

export type ProjectNode = ProjectFile | ProjectFolder;
