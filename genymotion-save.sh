VM="Galaxy Nexus - 4.2.2 - API 17 - 720x1280"
VM=6a5d9245-b751-47aa-b38d-989c5f1a9cfb

echo "VM is \"$VM\""
VBoxManage snapshot $VM take snap1 
