ROOT=true
VM="Galaxy Nexus - 4.2.2 - API 17 - 720x1280"
VM=6a5d9245-b751-47aa-b38d-989c5f1a9cfb

echo "VM is \"$VM\""
VBoxManage snapshot $VM restore snap1 
VBoxHeadless -s $VM &

IP=`./genymotion-detect-ip.sh`
echo $IP

#adb tcpip 5555
adb connect $IP:5555

#restart adb as root to allow powering it off
#root mode is generally what we want from a headless emulator (to download emma files for instance)
adb root
adb connect $IP:5555

